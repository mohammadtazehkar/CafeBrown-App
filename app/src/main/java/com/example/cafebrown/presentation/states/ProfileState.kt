package com.example.cafebrown.presentation.states

import com.example.cafebrown.utils.AppKeyboard


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

//    var response : Resource<APISignInResponse>
)
