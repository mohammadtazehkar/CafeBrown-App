package com.example.cafebrown.data.models.reserveHistory

import com.google.gson.annotations.SerializedName

data class APIGetUserReserveResponse(
    @SerializedName("status")
    val status: Int,
    @SerializedName("data")
    val data: List<GetUserReserveResponse>?,
    @SerializedName("message")
    val message: String
)
