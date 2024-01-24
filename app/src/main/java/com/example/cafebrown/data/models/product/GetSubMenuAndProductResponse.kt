package com.example.cafebrown.data.models.product

import com.google.gson.annotations.SerializedName

data class GetSubMenuAndProductResponse(
    @SerializedName("subMenus")
    val subMenuList: MutableList<SubMenuResponse>,
    @SerializedName("products")
    val productList: List<ProductResponse>
)
