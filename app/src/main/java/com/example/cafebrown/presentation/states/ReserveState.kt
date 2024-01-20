package com.example.cafebrown.presentation.states

data class ReserveState  (
    var yearsList: List<String>,
    var monthList: List<String>,
    var daysList: List<String>,
    var selectedYear : String = "1",
    var selectedMonth : String = "1",
    var selectedDay : String = "1320",
    var hoursList: List<String>,
    var minuteList: List<String>,
    var periodList: List<String>,
    var selectedHours : String = "12",
    var selectedMinute : String = "00",
    var selectedPeriod : String = "تا 1 ساعت",
    var isReservable : Boolean = true,
    var isLeap: Boolean = false,
    var isEdit : Boolean = false,
    var from : String = ""
)