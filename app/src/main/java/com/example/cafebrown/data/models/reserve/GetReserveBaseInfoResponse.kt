package com.example.cafebrown.data.models.reserve

import com.google.gson.annotations.SerializedName

data class GetReserveBaseInfoResponse (
    @SerializedName("balance")
    val balance :Long,

    @SerializedName("imageUrl")
    val imageUrl: String,

    @SerializedName("reserveTimes")
    val reserveTimeData : List<GetReserveTimeResponse> = emptyList()

)