package com.example.cafebrown.data.models.productDetail

import com.google.gson.annotations.SerializedName

data class GetCommentResponse(
    @SerializedName("text")
    val text: String,
    @SerializedName("dateTime")
    val dateTime: String,
    @SerializedName("rate")
    val rate: Int,
    @SerializedName("user")
    val user: String
)
