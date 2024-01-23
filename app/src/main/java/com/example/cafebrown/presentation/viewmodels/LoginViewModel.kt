package com.example.cafebrown.presentation.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cafebrown.R
import com.example.cafebrown.domain.usecase.PostMobileUseCase
import com.example.cafebrown.presentation.events.AppUIEvent
import com.example.cafebrown.presentation.events.LoginEvent
import com.example.cafebrown.presentation.states.LoginState
import com.example.cafebrown.utils.AppKeyboard
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


//class SignInViewModel @Inject constructor (private val signInUseCase: SignInUseCase) : ViewModel() {
@HiltViewModel
class LoginViewModel @Inject constructor (private val postMobileUseCase: PostMobileUseCase): ViewModel() {
    private val _loginState = mutableStateOf(
        LoginState(
            response = Resource.Error("")
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
        }
    }

    private fun login(
        onLoginCompleted: (String) -> Unit
    ) {
        if (loginState.value.mobileNumber.isEmpty()) {
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
        else if (loginState.value.mobileNumber.length != 10) {
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
        else if (loginState.value.mobileNumber.substring(0,1) != "9") {
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
            _loginState.value = loginState.value.copy(
                isLoading = true,
                response = Resource.Loading()
            )
            viewModelScope.launch {
                _loginState.value = loginState.value.copy(
                    response = postMobileUseCase.execute("0${loginState.value.mobileNumber}")
                )
                when(loginState.value.response.data?.status){
                    BAD_REQUEST -> {
                        _uiEventFlow.emit(
                            AppUIEvent.ShowMessage(
                                message = UIText.DynamicString(loginState.value.response.data?.message!!)
                            )
                        )
                    }
                    SUCCESS -> {
                        onLoginCompleted("0${loginState.value.mobileNumber}")
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
                _loginState.value = loginState.value.copy(
                    isLoading = false
                )
            }
        }
    }
}