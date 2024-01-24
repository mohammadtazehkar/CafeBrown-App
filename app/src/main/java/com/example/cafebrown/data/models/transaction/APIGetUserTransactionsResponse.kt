package com.example.cafebrown.data.models.transaction

import com.google.gson.annotations.SerializedName

data class APIGetUserTransactionsResponse(
    @SerializedName("status")
     var status: Int = 0,
    @SerializedName("data")
     val data: GetUserTransactionsResponse? = null,
    @SerializedName("message")
     var message: String? = null
)
