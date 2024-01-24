package com.example.cafebrown.data.models.reserve

import com.google.gson.annotations.SerializedName

data class APIGetReserveBaseInfoResponse (
    @SerializedName("status")
    val status: Int,

    @SerializedName("data")
    val data : GetReserveBaseInfoResponse?,

    @SerializedName("message")
    val message: String
)