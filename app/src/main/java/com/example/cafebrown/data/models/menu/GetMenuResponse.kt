package com.example.cafebrown.data.models.menu

import com.google.gson.annotations.SerializedName

data class GetMenuResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("imageUrl")
    val imageUrl: String
)
