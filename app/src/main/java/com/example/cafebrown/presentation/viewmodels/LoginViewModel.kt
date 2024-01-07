package com.example.cafebrown.presentation.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cafebrown.R
import com.example.cafebrown.presentation.events.AppUIEvent
import com.example.cafebrown.presentation.events.LoginEvent
import com.example.cafebrown.presentation.states.LoginState
import com.example.cafebrown.utils.AppKeyboard
import com.example.cafebrown.utils.UIText
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

//@HiltViewModel
//class SignInViewModel @Inject constructor (private val signInUseCase: SignInUseCase) : ViewModel() {
class LoginViewModel : ViewModel() {
    private val _loginState = mutableStateOf(
        LoginState(
//            response = Resource.Error("")
        )
    )
    val loginState: State<LoginState> = _loginState

    private val _uiEventFlow = MutableSharedFlow<AppUIEvent>()
    val uiEventFlow = _uiEventFlow.asSharedFlow()

    fun onEvent(event: LoginEvent) {
        when (event) {

            is LoginEvent.KeyboardOpen -> {
                _loginState.value = loginState.value.copy(
                    keyboardState = AppKeyboard.Opened
                )
            }
            is LoginEvent.KeyboardClose -> {
                _loginState.value = loginState.value.copy(
                    keyboardState = AppKeyboard.Closed
                )
            }
            is LoginEvent.UpdateMobileState -> {
                _loginState.value = loginState.value.copy(
                    mobileNumber = event.newValue
                )
            }
            is LoginEvent.MakeMobileEmpty -> {
                _loginState.value = loginState.value.copy(
                    mobileNumber = ""
                )
            }
            is LoginEvent.LoginClicked -> {
                login(onLoginCompleted = event.onNavigateToVerify)
            }
            is LoginEvent.UpdateLoading -> {
                _loginState.value = loginState.value.copy(
                    isLoading = event.status
                )
            }
        }
    }

    private fun login(
        onLoginCompleted: (String) -> Unit
    ) {
        if (_loginState.value.mobileNumber.isEmpty()) {
            viewModelScope.launch {
                _uiEventFlow.emit(
                    AppUIEvent.ShowMessage(
                        message = UIText.StringResource(
                            resId = R.string.empty_mobile_number,
                            _loginState.value.mobileNumber
                        )
                    )
                )
            }
        }
        else if (_loginState.value.mobileNumber.length != 10) {
            viewModelScope.launch {
                _uiEventFlow.emit(
                    AppUIEvent.ShowMessage(
                        message = UIText.StringResource(
                            resId = R.string.invalid_mobile_number,
                            _loginState.value.mobileNumber
                        )
                    )
                )
            }
        }
        else if (_loginState.value.mobileNumber.substring(0,1) != "9") {
            viewModelScope.launch {
                _uiEventFlow.emit(
                    AppUIEvent.ShowMessage(
                        message = UIText.StringResource(
                            resId = R.string.invalid_mobile_number,
                            _loginState.value.mobileNumber
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
                    onLoginCompleted("+98${loginState.value.mobileNumber}")
//                }
        }
    }
}