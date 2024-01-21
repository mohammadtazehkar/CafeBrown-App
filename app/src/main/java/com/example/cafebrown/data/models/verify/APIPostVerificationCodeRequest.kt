package com.example.cafebrown.data.models.verify

import com.google.gson.annotations.SerializedName

data class APIPostVerificationCodeRequest(
    @SerializedName("verificationCode")
    var verificationCode: String,
    @SerializedName("mobileNumber")
    var mobileNumber: String
)
