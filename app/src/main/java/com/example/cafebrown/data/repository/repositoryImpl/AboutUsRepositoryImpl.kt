package com.example.cafebrown.data.repository.repositoryImpl

import android.util.Log
import com.example.cafebrown.data.models.APIGlobalResponse
import com.example.cafebrown.data.models.aboutUs.APIGetCoffeeShopDataResponse
import com.example.cafebrown.data.models.aboutUs.APIPostComplaintsRequest
import com.example.cafebrown.data.repository.datasource.AboutUsRemoteDataSource
import com.example.cafebrown.data.repository.datasource.AppLocalDataSource
import com.example.cafebrown.domain.repository.AboutUsRepository
import com.example.cafebrown.utils.JSonStatusCode
import com.example.cafebrown.utils.NetworkUtil
import com.example.cafebrown.utils.Resource
import com.example.cafebrown.utils.ServerConstants

class AboutUsRepositoryImpl(
    val aboutUsRemoteDataSource: AboutUsRemoteDataSource,
    val appLocalDataSource: AppLocalDataSource,
    val networkUtil: NetworkUtil
) : AboutUsRepository {
    override suspend fun getCoffeeShopData(): Resource<APIGetCoffeeShopDataResponse> {
        return if (networkUtil.isInternetAvailable()) {
            try {
                val token = appLocalDataSource.getTokenFromDB()
                val response = aboutUsRemoteDataSource.getCoffeeShopData(
                    token = "${ServerConstants.TOKEN_TYPE} $token"
                )
                if (response.isSuccessful && response.body() != null) {
                    Resource.Success(response.body()!!)
                } else {
                    if (response.code() == JSonStatusCode.EXPIRED_TOKEN) {
                        appLocalDataSource.deleteUserInfo()
                        Resource.Error(
                            "expired Token", APIGetCoffeeShopDataResponse(
                                response.code(), null, "expired Token"
                            )
                        )

                    } else {
                        Resource.Error(
                            "An error occurred", APIGetCoffeeShopDataResponse(
                                JSonStatusCode.SERVER_CONNECTION, null, "An error occurred"
                            )
                        )
                    }

                }
            } catch (e: Exception) {
                Resource.Error(
                    e.message ?: "An error occurred", APIGetCoffeeShopDataResponse(
                        JSonStatusCode.SERVER_CONNECTION, null, "An error occurred"
                    )
                )
            }
        } else {
            Resource.Error(
                "No internet connection", APIGetCoffeeShopDataResponse(
                    JSonStatusCode.INTERNET_CONNECTION, null, "No internet connection"
                )
            )
        }
    }

    override suspend fun postComplaints(apiPostComplaintsRequest: APIPostComplaintsRequest): Resource<APIGlobalResponse> {
        return if (networkUtil.isInternetAvailable()) {
            try {
                val token = appLocalDataSource.getTokenFromDB()
                val response = aboutUsRemoteDataSource.postComplaints(
                    "${ServerConstants.TOKEN_TYPE} $token", apiPostComplaintsRequest
                )
                if (response.isSuccessful && response.body() != null) {
                    Resource.Success(response.body()!!)
                } else {
                    if (response.code() == JSonStatusCode.EXPIRED_TOKEN) {
                        appLocalDataSource.deleteUserInfo()
                        Resource.Error(
                            "expired Token", APIGlobalResponse(
                                response.code(), "", "expired Token"
                            )
                        )

                    } else {
                        Resource.Error(
                            "An error occurred", APIGlobalResponse(
                                JSonStatusCode.SERVER_CONNECTION, "", "An error occurred"
                            )
                        )
                    }

                }
            } catch (e: Exception) {
                Resource.Error(
                    e.message ?: "An error occurred", APIGlobalResponse(
                        JSonStatusCode.SERVER_CONNECTION, "", "An error occurred"
                    )
                )
            }
        } else {
            Resource.Error(
                "No internet connection", APIGlobalResponse(
                    JSonStatusCode.INTERNET_CONNECTION, "", "No internet connection"
                )
            )
        }
    }
}