package com.example.cafebrown.domain.usecase

import com.example.cafebrown.data.models.APIGlobalResponse
import com.example.cafebrown.data.models.productDetail.APIPostCommentRequest
import com.example.cafebrown.domain.repository.ProductDetailRepository
import com.example.cafebrown.utils.Resource


class PostCommentUseCase(private val productDetailRepository: ProductDetailRepository) {
    suspend fun execute(postCommentRequest: APIPostCommentRequest): Resource<APIGlobalResponse> = productDetailRepository.postComment(postCommentRequest)

}