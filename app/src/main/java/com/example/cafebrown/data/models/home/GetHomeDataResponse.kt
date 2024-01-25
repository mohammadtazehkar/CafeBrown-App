package com.example.cafebrown.data.models.home

import com.google.gson.annotations.SerializedName

data class GetHomeDataResponse(

    @SerializedName("images")
    val imageList: List<HomeImage>

)
