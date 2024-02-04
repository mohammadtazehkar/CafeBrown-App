package com.example.cafebrown.data.repository.datasourceImpl

import com.example.cafebrown.data.api.APIService
import com.example.cafebrown.data.models.APIGlobalResponse
import com.example.cafebrown.data.models.transaction.APIGetUserTransactionsResponse
import com.example.cafebrown.data.models.transaction.APIPostIncreaseBalanceRequest
import com.example.cafebrown.data.repository.datasource.TransactionRemoteDataSource
import retrofit2.Response

class TransactionRemoteDataSourceImpl(private val apiService: APIService) :
    TransactionRemoteDataSource {
    override suspend fun getTransactionList(token: String): Response<APIGetUserTransactionsResponse> {
        return apiService.getUserTransactions(token)
    }

    override suspend fun postIncreaseBalance(
        token: String, increaseBalanceObject: APIPostIncreaseBalanceRequest
    ): Response<APIGlobalResponse> {
        return apiService.postIncreaseBalance(token, increaseBalanceObject)
    }
}