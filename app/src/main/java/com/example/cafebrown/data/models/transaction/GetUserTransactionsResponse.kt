package com.example.cafebrown.data.models.transaction

import com.google.gson.annotations.SerializedName

data class GetUserTransactionsResponse(
    @SerializedName("balance")
    var balance: Long? = null,
    @SerializedName("transactions")
    var transactionsList: MutableList<UserTransactionsData>
)
