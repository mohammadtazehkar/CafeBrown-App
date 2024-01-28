package com.example.cafebrown.data.models.productDetail

import com.google.gson.annotations.SerializedName

data class APIPostCommentRequest(
    @SerializedName("text")
    val text: String,
    @SerializedName("rate")
    val rate: Int,
    @SerializedName("productId")
    val productId: Int
)
