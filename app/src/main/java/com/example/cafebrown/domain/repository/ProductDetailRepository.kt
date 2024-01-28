package com.example.cafebrown.domain.repository

import com.example.cafebrown.data.models.APIGlobalResponse
import com.example.cafebrown.data.models.productDetail.APIGetCommentResponse
import com.example.cafebrown.data.models.productDetail.APIGetProductDetailResponse
import com.example.cafebrown.data.models.productDetail.APIPostCommentRequest
import com.example.cafebrown.utils.Resource

interface ProductDetailRepository {
    suspend fun getProductDetail(productId: Int): Resource<APIGetProductDetailResponse>
    suspend fun getCommentList(productId: Int): Resource<APIGetCommentResponse>
    suspend fun postComment(postCommentRequest: APIPostCommentRequest): Resource<APIGlobalResponse>
}