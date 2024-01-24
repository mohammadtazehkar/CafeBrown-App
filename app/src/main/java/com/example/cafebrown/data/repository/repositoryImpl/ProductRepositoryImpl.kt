package com.example.cafebrown.data.repository.repositoryImpl

import android.util.Log
import com.example.cafebrown.data.models.product.APIGetSubMenuAndProductResponse
import com.example.cafebrown.data.repository.datasource.AppLocalDataSource
import com.example.cafebrown.data.repository.datasource.ProductRemoteDataSource
import com.example.cafebrown.domain.repository.ProductRepository
import com.example.cafebrown.utils.JSonStatusCode.EXPIRED_TOKEN
import com.example.cafebrown.utils.JSonStatusCode.INTERNET_CONNECTION
import com.example.cafebrown.utils.JSonStatusCode.SERVER_CONNECTION
import com.example.cafebrown.utils.NetworkUtil
import com.example.cafebrown.utils.Resource
import com.example.cafebrown.utils.ServerConstants

class ProductRepositoryImpl (
    private val productRemoteDataSource: ProductRemoteDataSource,
    private val appLocalDataSource: AppLocalDataSource,
    private val networkUtil: NetworkUtil
): ProductRepository {
    override suspend fun getSubCategoryAndProductList(menuId: Int): Resource<APIGetSubMenuAndProductResponse> {
        return if (networkUtil.isInternetAvailable()) {
            try {
                val token = appLocalDataSource.getTokenFromDB()
                val response = productRemoteDataSource.getSubMenuAndProductList(
                    token = "${ServerConstants.TOKEN_TYPE} $token",
                    menuId = menuId
                )
                if (response.isSuccessful && response.body() != null) {
                    Resource.Success(response.body()!!)
                }
                else {
                    if (response.code() == EXPIRED_TOKEN){
                        appLocalDataSource.deleteUserInfo()
                        Resource.Error("expired Token",
                            APIGetSubMenuAndProductResponse(response.code(),
                                null,
                                "expired Token"
                            )
                        )
                    }
                    else{
                        Resource.Error(
                            "An error occurred",
                            APIGetSubMenuAndProductResponse(
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
                    APIGetSubMenuAndProductResponse(
                        SERVER_CONNECTION,
                        null,
                        "An error occurred"
                    )
                )
            }
        } else {
            Resource.Error(
                "No internet connection",
                APIGetSubMenuAndProductResponse(
                    INTERNET_CONNECTION,
                    null,
                    "No internet connection"
                )
            )
        }
    }
}