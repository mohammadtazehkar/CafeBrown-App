package com.example.cafebrown.data.repository.datasource

import com.example.cafebrown.data.models.APIGlobalResponse
import com.example.cafebrown.data.models.transaction.APIGetUserTransactionsResponse
import com.example.cafebrown.data.models.transaction.APIPostIncreaseBalanceRequest
import retrofit2.Response

interface TransactionRemoteDataSource {
    suspend fun getTransactionList(token: String): Response<APIGetUserTransactionsResponse>
    suspend fun postIncreaseBalance(
        token: String,
        increaseBalanceObject: APIPostIncreaseBalanceRequest
    ): Response<APIGlobalResponse>
}