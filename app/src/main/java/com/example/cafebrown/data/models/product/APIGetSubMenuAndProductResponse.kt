package com.example.cafebrown.data.models.product

import com.google.gson.annotations.SerializedName

data class APIGetSubMenuAndProductResponse(
    @SerializedName("status")
    val status: Int,
    @SerializedName("data")
    val data: GetSubMenuAndProductResponse?,
    @SerializedName("message")
    val message: String
)
