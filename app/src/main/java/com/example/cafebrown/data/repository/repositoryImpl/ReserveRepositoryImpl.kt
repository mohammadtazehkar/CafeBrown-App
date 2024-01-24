package com.example.cafebrown.data.repository.repositoryImpl

import com.example.cafebrown.data.models.desk.APIGetDeskResponse
import com.example.cafebrown.data.models.reserve.APIGetReserveBaseInfoResponse
import com.example.cafebrown.data.repository.datasource.AppLocalDataSource
import com.example.cafebrown.data.repository.datasource.ReserveRemoteDataSource
import com.example.cafebrown.domain.repository.ReserveRepository
import com.example.cafebrown.utils.JSonStatusCode
import com.example.cafebrown.utils.NetworkUtil
import com.example.cafebrown.utils.Resource
import com.example.cafebrown.utils.ServerConstants

class ReserveRepositoryImpl(
    private val reserveRemoteDataSource: ReserveRemoteDataSource,
    private val appLocalDataSource: AppLocalDataSource,
    private val networkUtil: NetworkUtil
) : ReserveRepository {
    override suspend fun getReserveTimes(tableId: Int): Resource<APIGetReserveBaseInfoResponse> {
        return if (networkUtil.isInternetAvailable()) {
            try {
                val token = appLocalDataSource.getTokenFromDB()
                val response =
                    reserveRemoteDataSource.getReserveTimes(
                        "${ServerConstants.TOKEN_TYPE} $token",
                        tableId
                    )
                if (response.isSuccessful && response.body() != null) {
                    Resource.Success(response.body()!!)
                } else {
                    Resource.Error(
                        "An error occurred",
                        APIGetReserveBaseInfoResponse(
                            JSonStatusCode.SERVER_CONNECTION,
                            null,
                            "An error occurred"
                        )
                    )
                }
            } catch (e: Exception) {
                Resource.Error(
                    "No internet connection",
                    APIGetReserveBaseInfoResponse(
                        JSonStatusCode.SERVER_CONNECTION,
                        null,
                        "No internet connection"
                    )
                )
            }
        } else {
            Resource.Error(
                "No internet connection",
                APIGetReserveBaseInfoResponse(
                    JSonStatusCode.INTERNET_CONNECTION,
                    null,
                    "No internet connection"
                )
            )
        }
    }

}