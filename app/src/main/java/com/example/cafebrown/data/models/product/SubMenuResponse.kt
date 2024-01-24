package com.example.cafebrown.data.models.product

import com.google.gson.annotations.SerializedName

data class SubMenuResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    var isSelected: Boolean = false
)
