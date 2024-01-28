package com.example.cafebrown.data.repository.repositoryImpl

import com.example.cafebrown.data.models.reserveHistory.APIGetUserReserveResponse
import com.example.cafebrown.data.repository.datasource.AppLocalDataSource
import com.example.cafebrown.data.repository.datasource.ReserveHistoryRemoteDataSource
import com.example.cafebrown.domain.repository.ReserveHistoryRepository
import com.example.cafebrown.utils.JSonStatusCode.EXPIRED_TOKEN
import com.example.cafebrown.utils.JSonStatusCode.INTERNET_CONNECTION
import com.example.cafebrown.utils.JSonStatusCode.SERVER_CONNECTION
import com.example.cafebrown.utils.NetworkUtil
import com.example.cafebrown.utils.Resource
import com.example.cafebrown.utils.ServerConstants

class ReserveHistoryRepositoryImpl (
    private val reserveHistoryRemoteDataSource: ReserveHistoryRemoteDataSource,
    private val appLocalDataSource: AppLocalDataSource,
    private val networkUtil: NetworkUtil
): ReserveHistoryRepository {

    override suspend fun getReserveHistoryList(): Resource<APIGetUserReserveResponse> {
        return if (networkUtil.isInternetAvailable()) {
            try {
                val token = appLocalDataSource.getTokenFromDB()
                val response = reserveHistoryRemoteDataSource.getReserveHistoryList(
                    token = "${ServerConstants.TOKEN_TYPE} $token",
                )
                if (response.isSuccessful && response.body() != null) {
                    Resource.Success(response.body()!!)
                }
                else {
                    if (response.code() == EXPIRED_TOKEN){
                        appLocalDataSource.deleteUserInfo()
                        Resource.Error("expired Token",
                            APIGetUserReserveResponse(response.code(),
                                null,
                                "expired Token"
                            )
                        )
                    }
                    else{
                        Resource.Error(
                            "An error occurred",
                            APIGetUserReserveResponse(
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
                    APIGetUserReserveResponse(
                        SERVER_CONNECTION,
                        null,
                        "An error occurred"
                    )
                )
            }
        } else {
            Resource.Error(
                "No internet connection",
                APIGetUserReserveResponse(
                    INTERNET_CONNECTION,
                    null,
                    "No internet connection"
                )
            )
        }
    }
}