package com.example.cafebrown.presentation.states

import com.example.cafebrown.data.models.APIGlobalResponse
import com.example.cafebrown.utils.AppKeyboard
import com.example.cafebrown.utils.Resource


data class LoginState(
    var isBrandVisible: Boolean = true,
    var isLoading: Boolean = false,
    var keyboardState: AppKeyboard = AppKeyboard.Closed,
    var mobileNumber : String = "",
    var response : Resource<APIGlobalResponse>
)
