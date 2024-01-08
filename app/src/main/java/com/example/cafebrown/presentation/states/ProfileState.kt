package com.example.cafebrown.presentation.states

import com.example.cafebrown.utils.AppKeyboard


data class ProfileState(
    var isLoading: Boolean = false,
    var firstName : String = "",
    var lastName : String = "",
    var mobileNumber : String = "",
    var year : String = "",
    var month : String = "",
    var day : String = "",
//    var response : Resource<APISignInResponse>
)
