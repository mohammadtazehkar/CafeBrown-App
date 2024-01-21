package com.example.cafebrown.presentation.states

import com.example.cafebrown.data.models.APIGlobalResponse
import com.example.cafebrown.data.models.verify.PostVerificationCodeResponse
import com.example.cafebrown.utils.AppKeyboard
import com.example.cafebrown.utils.Resource


data class ProfileState(
    var isLoading: Boolean = false,
    var isLeap: Boolean = false,
    var firstName : String = "",
    var lastName : String = "",
    var mobileNumber : String = "",
    var gender : Boolean = true,
    var yearsList: List<String>,
    var monthList: List<String>,
    var daysList: List<String>,
    var selectedYear : String = "1",
    var selectedMonth : String = "1",
    var selectedDay : String = "1320",
    var from : String = "",
//    var dbInfo: PostVerificationCodeResponse,
    var responseUpdate : Resource<APIGlobalResponse>
)
