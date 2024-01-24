package com.example.cafebrown.data.repository.repositoryImpl

import android.util.Log
import com.example.cafebrown.data.models.transaction.APIGetUserTransactionsResponse
import com.example.cafebrown.data.repository.datasource.AppLocalDataSource
import com.example.cafebrown.data.repository.datasource.TransactionRemoteDataSource
import com.example.cafebrown.domain.repository.TransactionRepository
import com.example.cafebrown.utils.JSonStatusCode.EXPIRED_TOKEN
import com.example.cafebrown.utils.JSonStatusCode.INTERNET_CONNECTION
import com.example.cafebrown.utils.JSonStatusCode.SERVER_CONNECTION
import com.example.cafebrown.utils.NetworkUtil
import com.example.cafebrown.utils.Resource
import com.example.cafebrown.utils.ServerConstants.TOKEN_TYPE

class TransactionRepositoryImpl(
    val transactionRemoteDataSource: TransactionRemoteDataSource,
    val appLocalDataSource: AppLocalDataSource,
    val networkUtil: NetworkUtil
) :
    TransactionRepository {
    override suspend fun getTransactionList(): Resource<APIGetUserTransactionsResponse> {
        return if (networkUtil.isInternetAvailable()) {
            try {
                val token = appLocalDataSource.getTokenFromDB()
                val response = transactionRemoteDataSource.getTransactionList(
                    token = "$TOKEN_TYPE $token",
                )
                if (response.isSuccessful && response.body() != null) {
                    Resource.Success(response.body()!!)
                } else {
                    if (response.code() == EXPIRED_TOKEN) {
//                        appLocalDataSource.deleteUserInfo()
                        Resource.Error(
                            "expired Token",
                            APIGetUserTransactionsResponse(
                                response.code(),
                                null,
                                "expired Token"
                            )
                        )

                    } else {
                        Resource.Error(
                            "An error occurred",
                            APIGetUserTransactionsResponse(
                                SERVER_CONNECTION,
                                null,
                                "An error occurred"
                            )
                        )
                    }

                }
            } catch (e: Exception) {
                Resource.Error(
                    e.message ?: "An error occurred",
                    APIGetUserTransactionsResponse(
                        SERVER_CONNECTION,
                        null,
                        "An error occurred"
                    )
                )
            }
        } else {
            Resource.Error(
                "No internet connection",
                APIGetUserTransactionsResponse(
                    INTERNET_CONNECTION,
                    null,
                    "No internet connection"
                )
            )
        }
    }
}