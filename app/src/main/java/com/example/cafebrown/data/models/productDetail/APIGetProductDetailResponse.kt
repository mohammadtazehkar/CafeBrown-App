package com.example.cafebrown.data.models.productDetail

import com.google.gson.annotations.SerializedName

data class APIGetProductDetailResponse(
    @SerializedName("status")
    val status: Int,
    @SerializedName("data")
    val data: GetProductDetailResponse?,
    @SerializedName("message")
    val message: String
)
