package com.example.cafebrown.presentation.events

sealed class VerifyEvent{
    data object KeyboardOpen : VerifyEvent()
    data object KeyboardClose : VerifyEvent()
    data class UpdateVerifyCodeState(val newValue: String,val label: String): VerifyEvent()
    data object MakeVerifyCodeEmpty: VerifyEvent()
    data class VerifyClicked(val onNavigateToProfile: () -> Unit,val onNavigateToHome: () -> Unit) : VerifyEvent()
    data class UpdateLoading(val status: Boolean) : VerifyEvent()
    data object AcceptRules : VerifyEvent()
    data class UpdateRulesDialogVisibility(val status: Boolean) : VerifyEvent()
    data object UpdateTimeLeft: VerifyEvent()
    data class UpdateResendCodeState(val status: Boolean): VerifyEvent()
    data class UpdateActionLabel(val text: String): VerifyEvent()

}
