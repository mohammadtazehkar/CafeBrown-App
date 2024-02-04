package com.example.cafebrown.data.models.aboutUs

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class APIPostComplaintsRequest(
    @SerializedName("text")
    val text: String? = null
)
