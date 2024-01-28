package com.example.cafebrown.data.repository.repositoryImpl

import com.example.cafebrown.data.models.APIGlobalResponse
import com.example.cafebrown.data.models.productDetail.APIGetCommentResponse
import com.example.cafebrown.data.models.productDetail.APIGetProductDetailResponse
import com.example.cafebrown.data.models.productDetail.APIPostCommentRequest
import com.example.cafebrown.data.repository.datasource.AppLocalDataSource
import com.example.cafebrown.data.repository.datasource.ProductDetailRemoteDataSource
import com.example.cafebrown.domain.repository.ProductDetailRepository
import com.example.cafebrown.utils.JSonStatusCode.EXPIRED_TOKEN
import com.example.cafebrown.utils.JSonStatusCode.INTERNET_CONNECTION
import com.example.cafebrown.utils.JSonStatusCode.SERVER_CONNECTION
import com.example.cafebrown.utils.NetworkUtil
import com.example.cafebrown.utils.Resource
import com.example.cafebrown.utils.ServerConstants

class ProductDetailRepositoryImpl (
    private val productDetailRemoteDataSource: ProductDetailRemoteDataSource,
    private val appLocalDataSource: AppLocalDataSource,
    private val networkUtil: NetworkUtil
): ProductDetailRepository {

    override suspend fun getProductDetail(productId: Int): Resource<APIGetProductDetailResponse> {
        return if (networkUtil.isInternetAvailable()) {
            try {
                val token = appLocalDataSource.getTokenFromDB()
                val response = productDetailRemoteDataSource.getProductDetail(
                    token = "${ServerConstants.TOKEN_TYPE} $token",
                    productId = productId
                )
                if (response.isSuccessful && response.body() != null) {
                    Resource.Success(response.body()!!)
                }
                else {
                    if (response.code() == EXPIRED_TOKEN){
                        appLocalDataSource.deleteUserInfo()
                        Resource.Error("expired Token",
                            APIGetProductDetailResponse(response.code(),
                                null,
                                "expired Token"
                            )
                        )
                    }
                    else{
                        Resource.Error(
                            "An error occurred",
                            APIGetProductDetailResponse(
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
                    APIGetProductDetailResponse(
                        SERVER_CONNECTION,
                        null,
                        "An error occurred"
                    )
                )
            }
        } else {
            Resource.Error(
                "No internet connection",
                APIGetProductDetailResponse(
                    INTERNET_CONNECTION,
                    null,
                    "No internet connection"
                )
            )
        }
    }

    override suspend fun getCommentList(productId: Int): Resource<APIGetCommentResponse> {
        return if (networkUtil.isInternetAvailable()) {
            try {
                val token = appLocalDataSource.getTokenFromDB()
                val response = productDetailRemoteDataSource.getComments(
                    token = "${ServerConstants.TOKEN_TYPE} $token",
                    productId = productId
                )
                if (response.isSuccessful && response.body() != null) {
                    Resource.Success(response.body()!!)
                }
                else {
                    if (response.code() == EXPIRED_TOKEN){
                        appLocalDataSource.deleteUserInfo()
                        Resource.Error("expired Token",
                            APIGetCommentResponse(response.code(),
                                null,
                                "expired Token"
                            )
                        )
                    }
                    else{
                        Resource.Error(
                            "An error occurred",
                            APIGetCommentResponse(
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
                    APIGetCommentResponse(
                        SERVER_CONNECTION,
                        null,
                        "An error occurred"
                    )
                )
            }
        } else {
            Resource.Error(
                "No internet connection",
                APIGetCommentResponse(
                    INTERNET_CONNECTION,
                    null,
                    "No internet connection"
                )
            )
        }
    }

    override suspend fun postComment(postCommentRequest: APIPostCommentRequest): Resource<APIGlobalResponse> {
        return if (networkUtil.isInternetAvailable()) {
            try {
                val token = appLocalDataSource.getTokenFromDB()
                val response = productDetailRemoteDataSource.postComment(
                    token = "${ServerConstants.TOKEN_TYPE} $token",
                    postCommentRequest = postCommentRequest
                )
                if (response.isSuccessful && response.body() != null) {
                    Resource.Success(response.body()!!)
                }
                else {
                    if (response.code() == EXPIRED_TOKEN){
                        appLocalDataSource.deleteUserInfo()
                        Resource.Error("expired Token",
                            APIGlobalResponse(response.code(),
                                "expired Token",
                                null
                            )
                        )
                    }
                    else{
                        Resource.Error(
                            "An error occurred",
                            APIGlobalResponse(
                                SERVER_CONNECTION,
                                "An error occurred",
                                null
                            )
                        )
                    }

                }
            } catch (e: Exception) {
                Resource.Error(
                    e.message ?: "An error occurred",
                    APIGlobalResponse(
                        SERVER_CONNECTION,
                        "An error occurred",
                        null
                    )
                )
            }
        } else {
            Resource.Error(
                "No internet connection",
                APIGlobalResponse(
                    INTERNET_CONNECTION,
                    "No internet connection",
                    null
                )
            )
        }
    }
}