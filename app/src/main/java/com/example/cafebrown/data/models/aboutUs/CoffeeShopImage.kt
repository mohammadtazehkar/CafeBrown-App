package com.example.cafebrown.data.models.aboutUs

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CoffeeShopImage(
    @SerializedName("url")
    var images: String? = null,

    @SerializedName("link")
    var link: String? = null
)
