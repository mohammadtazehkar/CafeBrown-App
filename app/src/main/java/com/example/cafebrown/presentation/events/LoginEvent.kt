package com.example.cafebrown.presentation.events

sealed class LoginEvent{
    data object KeyboardOpen : LoginEvent()
    data object KeyboardClose : LoginEvent()
    data class UpdateMobileState(val newValue: String): LoginEvent()
    data object MakeMobileEmpty: LoginEvent()
    data class LoginClicked(val onNavigateToVerify: (String) -> Unit) : LoginEvent()
    data class UpdateLoading(val status: Boolean) : LoginEvent()
}
