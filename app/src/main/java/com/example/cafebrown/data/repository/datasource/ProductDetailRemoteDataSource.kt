package com.example.cafebrown.data.repository.datasource

import com.example.cafebrown.data.models.productDetail.APIGetCommentResponse
import com.example.cafebrown.data.models.productDetail.APIGetProductDetailResponse
import retrofit2.Response

interface ProductDetailRemoteDataSource {
    suspend fun getProductDetail(token: String,productId: Int): Response<APIGetProductDetailResponse>
    suspend fun getComments(token: String,productId: Int): Response<APIGetCommentResponse>

}