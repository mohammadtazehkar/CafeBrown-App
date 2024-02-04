package com.example.cafebrown.data.models.aboutUs

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CoffeeShopData(
    @SerializedName("key")
    var key: String? = null,

    @SerializedName("value")
    var value: String? = null,

    @SerializedName("title")
    var title: String? = null
)
