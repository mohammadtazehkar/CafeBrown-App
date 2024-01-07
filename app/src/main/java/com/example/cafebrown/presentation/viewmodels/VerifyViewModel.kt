package com.example.cafebrown.presentation.viewmodels

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
            mobileNumber = savedStateHandle.get<String>(MOBILE_NUMBER)!!
//            response = Resource.Error("")
        )
    )
    val verifyState: State<VerifyState> = _verifyState

    private val _uiEventFlow = MutableSharedFlow<AppUIEvent>()
    val uiEventFlow = _uiEventFlow.asSharedFlow()

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
                    verifyCode = event.newValue
                )
            }
            is VerifyEvent.MakeVerifyCodeEmpty -> {
                _verifyState.value = verifyState.value.copy(
                    verifyCode = ""
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
        }
    }

    private fun verify(
        onVerificationCompleted: () -> Unit
    ) {
        if (_verifyState.value.verifyCode.isEmpty()) {
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
        else if (_verifyState.value.verifyCode.length != 6) {
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
        else if (_verifyState.value.mobileNumber.substring(0,1) != "9") {
            viewModelScope.launch {
                _uiEventFlow.emit(
                    AppUIEvent.ShowMessage(
                        message = UIText.StringResource(
                            resId = R.string.invalid_mobile_number,
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