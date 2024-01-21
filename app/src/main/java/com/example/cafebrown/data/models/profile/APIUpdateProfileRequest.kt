package com.example.cafebrown.data.models.profile

import com.google.gson.annotations.SerializedName

data class APIUpdateProfileRequest(
    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("lastName")
    val lastName: String,
    @SerializedName("birthDate")
    val birthDate: String,
    @SerializedName("sex")
    val sex: Boolean
)
