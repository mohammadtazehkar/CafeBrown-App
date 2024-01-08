package com.example.cafebrown.presentation.viewmodels

import android.text.format.DateUtils
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cafebrown.R
import com.example.cafebrown.presentation.events.AppUIEvent
import com.example.cafebrown.presentation.events.VerifyEvent
import com.example.cafebrown.presentation.states.VerifyState
import com.example.cafebrown.utils.AppKeyboard
import com.example.cafebrown.utils.ArgumentKeys.MOBILE_NUMBER
import com.example.cafebrown.utils.UIText
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

//@HiltViewModel
//class SignInViewModel @Inject constructor (private val signInUseCase: SignInUseCase) : ViewModel() {
class VerifyViewModel(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _verifyState = mutableStateOf(
        VerifyState(
            mobileNumber = savedStateHandle.get<String>(MOBILE_NUMBER)!!,
//            response = Resource.Error("")
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
                verify(onVerificationCompleted = event.onNavigateToProfile)
            }

            is VerifyEvent.UpdateLoading -> {
                _verifyState.value = verifyState.value.copy(
                    isLoading = event.status
                )
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
        onVerificationCompleted: () -> Unit
    ) {
        if (verifyState.value.isResendCodeState){
            startTimer()
            _verifyState.value = verifyState.value.copy(
                verifyCode = "",
                isResendCodeState = false,
                isTimerVisible = true
            )
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
//            _loginState.value = loginState.value.copy(
//                response = Resource.Loading()
//            )
//            viewModelScope.launch {
//                _loginState.value = loginState.value.copy(
//                    response = signInUseCase.execute(signInState.value.textFieldStates[USERNAME],signInState.value.textFieldStates[PASSWORD])
//                )
//                if (_loginState.value.response.data?.statusCode == INVALID_USERNAME){
//                    _uiEventFlow.emit(
//                        SignInUIEvent.ShowMessage(
//                            message = UIText.StringResource(
//                                resId = R.string.invalid_login_info,
//                                _signInState.value.textFieldStates[PASSWORD]
//                            )
//                        )
//                    )
//                }else if (_signInState.value.response.data?.statusCode == SUCCESS){
                onVerificationCompleted()
//                }
            }
        }
    }
}