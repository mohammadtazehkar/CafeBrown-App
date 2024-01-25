package com.example.cafebrown.presentation.states

import com.example.cafebrown.data.models.APIGlobalResponse
import com.example.cafebrown.data.models.transaction.APIGetUserTransactionsResponse
import com.example.cafebrown.data.models.transaction.UserTransactionsData
import com.example.cafebrown.utils.Resource

data class TransactionState(
    var isDialogVisible: Boolean = false,
    var balance: Long? = 0,
    var increaseBalance: String = "",
    var transactionList: MutableList<UserTransactionsData> = mutableListOf(),
    var isLoading: Boolean = false,
    var response: Resource<APIGetUserTransactionsResponse> = Resource.Error(""),
    var responsePost : Resource<APIGlobalResponse> = Resource.Error("")
)
