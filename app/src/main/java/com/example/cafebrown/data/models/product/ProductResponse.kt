package com.example.cafebrown.data.models.product

import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("price")
    val price: Long,
    @SerializedName("maxOfferCount")
    val maxOfferCount: Int,
    @SerializedName("categoryId")
    val categoryId: Int,
    @SerializedName("url")
    val url: String
)
