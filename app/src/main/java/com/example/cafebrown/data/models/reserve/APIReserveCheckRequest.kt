package com.example.cafebrown.data.models.reserve

import com.google.gson.annotations.SerializedName

data class APIReserveCheckRequest(

    @SerializedName("tableId")
    val tableId: Int,

    @SerializedName("reserveTimeId")
    val reserveTimeId: Int,

    @SerializedName("date")
    val date: String,

    @SerializedName("time")
    val time: String
)
