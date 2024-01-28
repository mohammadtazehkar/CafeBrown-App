package com.example.cafebrown.data.models.reserveHistory

import com.google.gson.annotations.SerializedName

data class GetUserReserveResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("dateTime")
    val dateTime: String,
    @SerializedName("table")
    val table: String,
    @SerializedName("tableId")
    val tableId: Int
)
