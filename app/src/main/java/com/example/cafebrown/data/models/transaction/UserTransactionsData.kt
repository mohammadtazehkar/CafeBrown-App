package com.example.cafebrown.data.models.transaction

import com.google.gson.annotations.SerializedName

data class UserTransactionsData(
    @SerializedName("dateTime")
    var dateTime: String? = null,
    @SerializedName("comment")
    val comment: String? = null,
    @SerializedName("transactionType")
    val transactionType: String? = null,
    @SerializedName("transactionTypeId")
    val transactionTypeId: Int = 0,
    @SerializedName("amount")
    val amount: Long? = null,
    @SerializedName("reserveId")
    val reserveId: Int = 0,
    @SerializedName("status")
    var status: Boolean = false
)
