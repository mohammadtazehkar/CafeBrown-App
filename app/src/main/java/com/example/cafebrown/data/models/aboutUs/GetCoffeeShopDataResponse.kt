package com.example.cafebrown.data.models.aboutUs

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GetCoffeeShopDataResponse(
    @SerializedName("coffeeShopData")
    var coffeeShopDataList: List<CoffeeShopData?>? = null,

    @SerializedName("images")
    var coffeeShopImageList: MutableList<CoffeeShopImage?>? = null
)
