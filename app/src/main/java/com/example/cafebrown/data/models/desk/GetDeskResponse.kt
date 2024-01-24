package com.example.cafebrown.data.models.desk

import com.google.gson.annotations.SerializedName

data class GetDeskResponse(
    @SerializedName("id")
    var id: Int,

    @SerializedName("number")
    var number: Int,

    @SerializedName("capacity")
    var capacity: Int,

    @SerializedName("status")
    var status: Boolean,

    @SerializedName("title")
    var title: String,
)
