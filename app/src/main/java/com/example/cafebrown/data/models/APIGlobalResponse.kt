package com.example.cafebrown.data.models

import com.google.gson.annotations.SerializedName

data class APIGlobalResponse(
    @SerializedName("status")
    val status: Int,
    @SerializedName("data")
    val data: String,
    @SerializedName("message")
    val message: String?,
)
