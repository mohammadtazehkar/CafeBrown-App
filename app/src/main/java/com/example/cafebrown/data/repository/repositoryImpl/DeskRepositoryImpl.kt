package com.example.cafebrown.data.repository.repositoryImpl

import com.example.cafebrown.data.models.desk.APIGetDeskResponse
import com.example.cafebrown.data.models.home.APIGetHomeDataResponse
import com.example.cafebrown.data.repository.datasource.AppLocalDataSource
import com.example.cafebrown.data.repository.datasource.DeskRemoteDataSource
import com.example.cafebrown.data.repository.datasource.HomeRemoteDataSource
import com.example.cafebrown.domain.repository.DeskRepository
import com.example.cafebrown.utils.JSonStatusCode
import com.example.cafebrown.utils.NetworkUtil
import com.example.cafebrown.utils.Resource
import com.example.cafebrown.utils.ServerConstants

class DeskRepositoryImpl(
    private val deskRemoteDataSource: DeskRemoteDataSource,
    private val appLocalDataSource: AppLocalDataSource,
    private val networkUtil: NetworkUtil
) : DeskRepository {
    override suspend fun getDeskList(): Resource<APIGetDeskResponse> {
        return if (networkUtil.isInternetAvailable()) {
            try {
                val token = appLocalDataSource.getTokenFromDB()
                val response =
                    deskRemoteDataSource.getDeskList("${ServerConstants.TOKEN_TYPE} $token")
                if (response.isSuccessful && response.body() != null) {
                    Resource.Success(response.body()!!)
                } else {
                    Resource.Error(
                        "An error occurred",
                        APIGetDeskResponse(
                            JSonStatusCode.SERVER_CONNECTION,
                            null,
                            "An error occurred"
                        )
                    )
                }
            } catch (e: Exception) {
                Resource.Error(
                    "No internet connection",
                    APIGetDeskResponse(
                        JSonStatusCode.SERVER_CONNECTION,
                        null,
                        "No internet connection"
                    )
                )
            }
        } else {
            Resource.Error(
                "No internet connection",
                APIGetDeskResponse(
                    JSonStatusCode.INTERNET_CONNECTION,
                    null,
                    "No internet connection"
                )
            )
        }
    }
}