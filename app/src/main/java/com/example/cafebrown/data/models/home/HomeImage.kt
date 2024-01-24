package com.example.cafebrown.data.models.home

import com.google.gson.annotations.SerializedName

data class HomeImage(

    @SerializedName("url")
    val images: String,

    @SerializedName("link")
    val link: String

)
