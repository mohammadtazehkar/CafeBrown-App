package com.example.cafebrown.data.models.verify

import com.google.gson.annotations.SerializedName

data class APIPostVerificationCodeResponse(
    @SerializedName("status")
    val status: Int,
    @SerializedName("data")
    val data: PostVerificationCodeResponse?,
    @SerializedName("message")
    val message: String
)
