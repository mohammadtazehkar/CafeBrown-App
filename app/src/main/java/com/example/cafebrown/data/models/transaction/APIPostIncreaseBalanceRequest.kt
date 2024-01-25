package com.example.cafebrown.data.models.transaction

import com.google.gson.annotations.SerializedName

data class APIPostIncreaseBalanceRequest(
    @SerializedName("status")
    var status: String? = null,

    @SerializedName("refId")
    var refId: String? = null,

    @SerializedName("amount")
    var amount: Long? = null
)
