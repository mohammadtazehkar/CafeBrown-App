package com.example.cafebrown.data.models.productDetail

import com.google.gson.annotations.SerializedName

data class APIGetCommentResponse(
    @SerializedName("status")
    val status: Int,
    @SerializedName("data")
    val data: List<GetCommentResponse>?,
    @SerializedName("message")
    val message: String
)
