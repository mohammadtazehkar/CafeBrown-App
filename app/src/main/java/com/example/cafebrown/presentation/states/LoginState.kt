package com.example.cafebrown.presentation.states

import com.example.cafebrown.utils.AppKeyboard


data class LoginState(
    var isBrandVisible: Boolean = true,
    var isLoading: Boolean = false,
    var keyboardState: AppKeyboard = AppKeyboard.Closed,
    var mobileNumber : String = "",
//    var response : Resource<APISignInResponse>
)
