package com.example.cafebrown.presentation.viewmodels

import android.text.format.DateUtils
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cafebrown.R
import com.example.cafebrown.domain.usecase.PostMobileUseCase
import com.example.cafebrown.domain.usecase.PostVerificationCodeUseCase
import com.example.cafebrown.presentation.events.AppUIEvent
import com.example.cafebrown.presentation.events.VerifyEvent
import com.example.cafebrown.presentation.states.VerifyState
import com.example.cafebrown.utils.AppKeyboard
import com.example.cafebrown.utils.ArgumentKeys.MOBILE_NUMBER
import com.example.cafebrown.utils.JSonStatusCode
import com.example.cafebrown.utils.JSonStatusCode.BAD_REQUEST
import com.example.cafebrown.utils.JSonStatusCode.INTERNET_CONNECTION
import com.example.cafebrown.utils.JSonStatusCode.SERVER_CONNECTION
import com.example.cafebrown.utils.JSonStatusCode.SUCCESS
import com.example.cafebrown.utils.Resource
import com.example.cafebrown.utils.UIText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VerifyViewModel @Inject constructor(
    private val postVerificationCodeUseCase: PostVerificationCodeUseCase,
    private val postMobileUseCase: PostMobileUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _verifyState = mutableStateOf(
        VerifyState(
            mobileNumber = savedStateHandle.get<String>(MOBILE_NUMBER)!!,
            responseVerify = Resource.Error(""),
            responseMobile = Resource.Error("")
        )
    )
    val verifyState: State<VerifyState> = _verifyState

    private val _uiEventFlow = MutableSharedFlow<AppUIEvent>()
    val uiEventFlow = _uiEventFlow.asSharedFlow()

    init {
        startTimer()
    }

    fun onEvent(event: VerifyEvent) {
        when (event) {

            is VerifyEvent.KeyboardOpen -> {
                _verifyState.value = verifyState.value.copy(
                    keyboardState = AppKeyboard.Opened
                )
            }

            is VerifyEvent.KeyboardClose -> {
                _verifyState.value = verifyState.value.copy(
                    keyboardState = AppKeyboard.Closed
                )
            }

            is VerifyEvent.UpdateVerifyCodeState -> {
                _verifyState.value = verifyState.value.copy(
                    verifyCode = event.newValue,
                )
                if (verifyState.value.verifyCode.length == 6){
                    updateActionLabel(event.label)
                }
                _verifyState.value = verifyState.value.copy(
                    isTimerVisible = verifyState.value.verifyCode.length != 6
                )
            }

            is VerifyEvent.MakeVerifyCodeEmpty -> {
                _verifyState.value = verifyState.value.copy(
                    verifyCode = "",
                    isTimerVisible = true
                )
            }

            is VerifyEvent.VerifyClicked -> {
                verify(onNavigateToProfile = event.onNavigateToProfile, onNavigateToHome = event.onNavigateToHome)
            }

            is VerifyEvent.AcceptRules -> {
                _verifyState.value = verifyState.value.copy(
                    isRulesAccepted = true
                )
            }

            is VerifyEvent.UpdateRulesDialogVisibility -> {
                _verifyState.value = verifyState.value.copy(
                    isRulesDialogVisible = event.status
                )
            }

            is VerifyEvent.UpdateTimeLeft -> {
                    val duration = DateUtils.formatElapsedTime(verifyState.value.timeLeft / 1000)
                    _verifyState.value = verifyState.value.copy(
                        timeLeft = verifyState.value.timeLeft - 1000,
                        timer = duration
                    )
            }

            is VerifyEvent.UpdateResendCodeState -> {
                _verifyState.value = verifyState.value.copy(
                    verifyCode = "",
                    isResendCodeState = event.status,
                    isTimerVisible = false
                )
            }

            is VerifyEvent.UpdateActionLabel -> {
                updateActionLabel(event.text)
            }
        }
    }

    private fun updateActionLabel(text: String){
        _verifyState.value = verifyState.value.copy(
            actionLabel = text,
        )
    }
    private fun startTimer(){
        val duration = DateUtils.formatElapsedTime(verifyState.value.timeLeft / 1000)
        _verifyState.value = verifyState.value.copy(
            timeLeft = 90 * 1000,
            timer = duration
        )
    }

    private fun verify(
        onNavigateToProfile: () -> Unit,
        onNavigateToHome: () -> Unit
    ) {
        if (verifyState.value.isResendCodeState){
            _verifyState.value = verifyState.value.copy(
                isLoading = true,
                responseMobile = Resource.Loading()
            )
            viewModelScope.launch {
                _verifyState.value = verifyState.value.copy(
                    responseMobile = postMobileUseCase.execute(verifyState.value.mobileNumber)
                )
                when(verifyState.value.responseMobile.data?.status){
                    BAD_REQUEST -> {
                        _uiEventFlow.emit(
                            AppUIEvent.ShowMessage(
                                message = UIText.DynamicString(verifyState.value.responseMobile.data?.message!!)
                            )
                        )
                    }
                    SUCCESS -> {
                        startTimer()
                        _verifyState.value = verifyState.value.copy(
                            verifyCode = "",
                            isResendCodeState = false,
                            isTimerVisible = true
                        )
                    }
                    SERVER_CONNECTION ->{
                        _uiEventFlow.emit(
                            AppUIEvent.ShowMessage(
                                message = UIText.StringResource(R.string.connection_problem)
                            )
                        )
                    }
                    INTERNET_CONNECTION ->{
                        _uiEventFlow.emit(
                            AppUIEvent.ShowMessage(
                                message = UIText.StringResource(R.string.internet_connection_problem)
                            )
                        )
                    }
                }
                _verifyState.value = verifyState.value.copy(
                    isLoading = false
                )
            }
        }
        else{
            if (verifyState.value.verifyCode.isEmpty()) {
                viewModelScope.launch {
                    _uiEventFlow.emit(
                        AppUIEvent.ShowMessage(
                            message = UIText.StringResource(
                                resId = R.string.empty_verify_code,
                                _verifyState.value.verifyCode
                            )
                        )
                    )
                }
            }
            else if (verifyState.value.verifyCode.length != 6) {
                viewModelScope.launch {
                    _uiEventFlow.emit(
                        AppUIEvent.ShowMessage(
                            message = UIText.StringResource(
                                resId = R.string.invalid_verify_code,
                                _verifyState.value.verifyCode
                            )
                        )
                    )
                }
            }
            else if (!verifyState.value.isRulesAccepted) {
                viewModelScope.launch {
                    _uiEventFlow.emit(
                        AppUIEvent.ShowMessage(
                            message = UIText.StringResource(
                                resId = R.string.please_read_and_accept_rules,
                                _verifyState.value.verifyCode
                            )
                        )
                    )
                }
            }
            else {
                _verifyState.value = verifyState.value.copy(
                    isLoading = true,
                    responseVerify = Resource.Loading()
                )
                viewModelScope.launch {
                    _verifyState.value = verifyState.value.copy(
                        responseVerify = postVerificationCodeUseCase.execute(
                            verifyState.value.mobileNumber,
                            verifyState.value.verifyCode
                        )
                    )
                    when(verifyState.value.responseVerify.data?.status){
                        BAD_REQUEST -> {
                            _uiEventFlow.emit(
                                AppUIEvent.ShowMessage(
                                    message = UIText.DynamicString(verifyState.value.responseVerify.data?.message!!)
                                )
                            )
                        }
                        SUCCESS -> {
                            if (_verifyState.value.responseVerify.data?.data?.firstName?.isEmpty()!!) {
                                onNavigateToProfile()
                            }else{
                                onNavigateToHome()
                            }
                        }
                        SERVER_CONNECTION ->{
                            _uiEventFlow.emit(
                                AppUIEvent.ShowMessage(
                                    message = UIText.StringResource(R.string.connection_problem)
                                )
                            )
                        }
                        INTERNET_CONNECTION ->{
                            _uiEventFlow.emit(
                                AppUIEvent.ShowMessage(
                                    message = UIText.StringResource(R.string.internet_connection_problem)
                                )
                            )
                        }
                    }
                    _verifyState.value = verifyState.value.copy(
                        isLoading = false
                    )
                }
            }
        }
    }
}