package com.example.cafebrown.presentation.states

import com.example.cafebrown.data.models.desk.APIGetDeskResponse
import com.example.cafebrown.data.models.reserve.APIGetReserveBaseInfoResponse
import com.example.cafebrown.data.models.reserve.GetReserveTimeResponse
import com.example.cafebrown.utils.Resource

data class ReserveState  (
    var tableId:Int,
    var capacity: Int,
    var status: Boolean,
    var number: Int,
    var isLoading: Boolean = false,
    var response: Resource<APIGetReserveBaseInfoResponse>,
    var yearsList: List<String>,
    var monthList: List<String>,
    var daysList: List<String>,
    var selectedYear : String = "1",
    var selectedMonth : String = "1",
    var selectedDay : String = "1320",
    var hoursList: List<String>,
    var minuteList: List<String>,
    var periodList: List<GetReserveTimeResponse> = emptyList(),
    var selectedHours : String = "12",
    var selectedMinute : String = "00",
    var selectedPeriod : String = "تا 1 ساعت",
    var isReservable : Boolean = true,
    var isLeap: Boolean = false,
    var isEdit : Boolean = false,
    var from : String = "",
    var isReserveTimeChecked: Boolean = false
)