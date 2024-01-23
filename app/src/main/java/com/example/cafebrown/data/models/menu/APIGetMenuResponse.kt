package com.example.cafebrown.data.models.menu

import com.google.gson.annotations.SerializedName

data class APIGetMenuResponse(
    @SerializedName("status")
    val status: Int,
    @SerializedName("data")
    val data: List<GetMenuResponse>?,
    @SerializedName("message")
    val message: String
)
