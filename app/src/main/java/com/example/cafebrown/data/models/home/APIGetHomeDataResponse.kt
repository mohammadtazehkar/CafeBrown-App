package com.example.cafebrown.data.models.home

import com.google.gson.annotations.SerializedName

data class APIGetHomeDataResponse(

    @SerializedName("status")
    val status: Int,

    @SerializedName("data")
    val data: GetHomeDataResponse?,

    @SerializedName("message")
    val message: String

)
