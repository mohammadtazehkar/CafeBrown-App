package com.example.cafebrown.data.repository.repositoryImpl

import android.util.Log
import com.example.cafebrown.data.models.APIGlobalResponse
import com.example.cafebrown.data.models.home.APIGetHomeDataResponse
import com.example.cafebrown.data.repository.datasource.AppLocalDataSource
import com.example.cafebrown.data.repository.datasource.HomeRemoteDataSource
import com.example.cafebrown.data.repository.datasource.LoginRemoteDataSource
import com.example.cafebrown.domain.repository.HomeRepository
import com.example.cafebrown.domain.repository.LoginRepository
import com.example.cafebrown.utils.JSonStatusCode
import com.example.cafebrown.utils.NetworkUtil
import com.example.cafebrown.utils.Resource
import com.example.cafebrown.utils.ServerConstants

class HomeRepositoryImpl(
    private val homeRemoteDataSource: HomeRemoteDataSource,
    private val appLocalDataSource: AppLocalDataSource,
    private val networkUtil: NetworkUtil
) : HomeRepository {

    override suspend fun getHomeData(): Resource<APIGetHomeDataResponse> {
        return if (networkUtil.isInternetAvailable()) {
            try {
                val token = appLocalDataSource.getTokenFromDB()
                Log.i("Meyti", token)
                val response = homeRemoteDataSource.getHomeData("${ServerConstants.TOKEN_TYPE} $token")
                if (response.isSuccessful && response.body() != null) {
                    Resource.Success(response.body()!!)
                } else {
                    Resource.Error(
                        "An error occurred",
                        APIGetHomeDataResponse(
                            JSonStatusCode.SERVER_CONNECTION,
                            null,
                            "An error occurred"
                        )
                    )
                }
            } catch (e: Exception) {
                Resource.Error(
                    e.message ?: "An error occurred",
                    APIGetHomeDataResponse(JSonStatusCode.SERVER_CONNECTION,
                        null,
                        "An error occurred"
                    )
                )
            }
        } else {
            Resource.Error(
                "No internet connection",
                APIGetHomeDataResponse(
                    JSonStatusCode.INTERNET_CONNECTION,
                    null,
                    "No internet connection"
                )
            )
        }
    }
}