package com.example.cafebrown.presentation.events

sealed class VerifyEvent{
    data object KeyboardOpen : VerifyEvent()
    data object KeyboardClose : VerifyEvent()
    data class UpdateVerifyCodeState(val newValue: String): VerifyEvent()
    data object MakeVerifyCodeEmpty: VerifyEvent()
    data class VerifyClicked(val onNavigateToProfile: () -> Unit) : VerifyEvent()
    data class UpdateLoading(val status: Boolean) : VerifyEvent()
}
