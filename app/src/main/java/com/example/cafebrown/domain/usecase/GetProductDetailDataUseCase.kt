package com.example.cafebrown.domain.usecase

import com.example.cafebrown.data.models.productDetail.APIGetProductDetailResponse
import com.example.cafebrown.domain.repository.ProductDetailRepository
import com.example.cafebrown.utils.Resource

class GetProductDetailDataUseCase(private val productDetailRepository: ProductDetailRepository) {
    suspend fun execute(productId: Int): Resource<APIGetProductDetailResponse> = productDetailRepository.getProductDetail(productId)
}