package com.example.cafebrown.data.models.desk

import com.example.cafebrown.data.models.home.GetHomeDataResponse
import com.google.gson.annotations.SerializedName

data class APIGetDeskResponse(

    @SerializedName("status")
    val status: Int,

    @SerializedName("data")
    val data: List<GetDeskResponse>?,

    @SerializedName("message")
    val message: String
)
