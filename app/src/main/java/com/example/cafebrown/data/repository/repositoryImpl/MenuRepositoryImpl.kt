package com.example.cafebrown.data.repository.repositoryImpl

import com.example.cafebrown.data.models.menu.APIGetMenuResponse
import com.example.cafebrown.data.repository.datasource.AppLocalDataSource
import com.example.cafebrown.data.repository.datasource.MenuRemoteDataSource
import com.example.cafebrown.domain.repository.MenuRepository
import com.example.cafebrown.utils.JSonStatusCode.EXPIRED_TOKEN
import com.example.cafebrown.utils.JSonStatusCode.INTERNET_CONNECTION
import com.example.cafebrown.utils.JSonStatusCode.SERVER_CONNECTION
import com.example.cafebrown.utils.NetworkUtil
import com.example.cafebrown.utils.Resource
import com.example.cafebrown.utils.ServerConstants.TOKEN_TYPE

class MenuRepositoryImpl(
    private val menuRemoteDataSource: MenuRemoteDataSource,
    private val appLocalDataSource: AppLocalDataSource,
    private val networkUtil: NetworkUtil
) : MenuRepository {

    override suspend fun getMenuList(): Resource<APIGetMenuResponse> {
        return if (networkUtil.isInternetAvailable()) {
            try {
                val token = appLocalDataSource.getTokenFromDB()
                val response = menuRemoteDataSource.getMenuList(
                    token = "$TOKEN_TYPE $token",
                )
                if (response.isSuccessful && response.body() != null) {
                    Resource.Success(response.body()!!)
                } else {
                    if (response.code() == EXPIRED_TOKEN){
                        appLocalDataSource.deleteUserInfo()
                        Resource.Error("expired Token",
                            APIGetMenuResponse(response.code(),
                                null,
                                "expired Token"
                            )
                        )
                    }else{
                        Resource.Error(
                            "An error occurred",
                            APIGetMenuResponse(SERVER_CONNECTION,
                                null,
                                "An error occurred"
                            )
                        )
                    }

                }
            } catch (e: Exception) {
                Resource.Error(
                    e.message ?: "An error occurred",
                    APIGetMenuResponse(SERVER_CONNECTION,
                        null,
                        "An error occurred"
                    )
                )
            }
        } else {
            Resource.Error(
                "No internet connection",
                APIGetMenuResponse(INTERNET_CONNECTION,
                    null,
                    "No internet connection"
                )
            )
        }
    }
}