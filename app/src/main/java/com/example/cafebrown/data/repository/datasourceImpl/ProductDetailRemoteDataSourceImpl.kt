package com.example.cafebrown.data.repository.datasourceImpl

import com.example.cafebrown.data.api.APIService
import com.example.cafebrown.data.models.APIGlobalResponse
import com.example.cafebrown.data.models.productDetail.APIGetCommentResponse
import com.example.cafebrown.data.models.productDetail.APIGetProductDetailResponse
import com.example.cafebrown.data.models.productDetail.APIPostCommentRequest
import com.example.cafebrown.data.repository.datasource.ProductDetailRemoteDataSource
import retrofit2.Response

class ProductDetailRemoteDataSourceImpl(
    private val apiService: APIService
):ProductDetailRemoteDataSource {
    override suspend fun getProductDetail(
        token: String,
        productId: Int
    ): Response<APIGetProductDetailResponse> = apiService.getProductDetail(token, productId)

    override suspend fun getComments(
        token: String,
        productId: Int
    ): Response<APIGetCommentResponse> = apiService.getComments(token, productId)

    override suspend fun postComment(
        token: String,
        postCommentRequest: APIPostCommentRequest
    ): Response<APIGlobalResponse> = apiService.postComment(token, postCommentRequest)
}