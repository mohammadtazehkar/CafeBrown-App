package com.example.cafebrown.data.models.aboutUs

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class APIGetCoffeeShopDataResponse(
    @SerializedName("status")
    var status: Int = 0,

    @SerializedName("data")
    var data: GetCoffeeShopDataResponse? = null,

    @SerializedName("message")
    var message: String? = null
)
