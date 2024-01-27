package com.example.cafebrown.data.models.productDetail

import com.google.gson.annotations.SerializedName

data class GetProductDetailResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("price")
    val price: Long,
    @SerializedName("description")
    val description: String,
    @SerializedName("rating")
    val rating: Int,
    @SerializedName("maxOfferCount")
    val maxOfferCount: Int,
    @SerializedName("tutorial")
    val tutorial: String,
    @SerializedName("categoryId")
    val categoryId: Int,
    @SerializedName("category")
    val category: String,
    @SerializedName("productImages")
    val productImageList : List<String>
)
