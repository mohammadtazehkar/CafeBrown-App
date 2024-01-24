package com.example.cafebrown.data.repository.datasource

import com.example.cafebrown.data.models.transaction.APIGetUserTransactionsResponse
import retrofit2.Response

interface TransactionRemoteDataSource {
    suspend fun getTransactionList(token: String): Response<APIGetUserTransactionsResponse>
}