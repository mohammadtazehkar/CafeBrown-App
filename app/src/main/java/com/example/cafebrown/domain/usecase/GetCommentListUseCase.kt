package com.example.cafebrown.domain.usecase

import com.example.cafebrown.data.models.productDetail.APIGetCommentResponse
import com.example.cafebrown.domain.repository.ProductDetailRepository
import com.example.cafebrown.utils.Resource

class GetCommentListUseCase(private val productDetailRepository: ProductDetailRepository) {
    suspend fun execute(productId: Int): Resource<APIGetCommentResponse> = productDetailRepository.getCommentList(productId)
}