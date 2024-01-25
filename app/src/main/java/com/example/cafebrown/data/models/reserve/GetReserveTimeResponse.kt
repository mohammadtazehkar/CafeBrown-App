package com.example.cafebrown.data.models.reserve

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GetReserveTimeResponse (

    @SerializedName("id")
    val id : Int,

    @SerializedName("title")
    val title: String,

    @SerializedName("cost")
    val cost :Int
)