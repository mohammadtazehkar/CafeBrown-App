package com.example.cafebrown.presentation.states

import com.example.cafebrown.utils.AppKeyboard


data class VerifyState(
    var isBrandVisible: Boolean = true,
    var isLoading: Boolean = false,
    var keyboardState: AppKeyboard = AppKeyboard.Closed,
    var mobileNumber : String = "",
    var verifyCode : String = "",
//    var response : Resource<APISignInResponse>
)
