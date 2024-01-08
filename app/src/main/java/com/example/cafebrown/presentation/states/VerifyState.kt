package com.example.cafebrown.presentation.states

import com.example.cafebrown.utils.AppKeyboard


data class VerifyState(
    var isBrandVisible: Boolean = true,
    var isLoading: Boolean = false,
    var isRulesAccepted: Boolean = false,
    var isRulesDialogVisible: Boolean = false,
    var isResendCodeState: Boolean = false,
    var keyboardState: AppKeyboard = AppKeyboard.Closed,
    var mobileNumber : String = "",
    var verifyCode : String = "",
    var timeLeft : Long = 0L,
    var actionLabel : String = "",
    var isTimerVisible: Boolean = true,
    var timer: String = ""
//    var response : Resource<APISignInResponse>
)
