package com.example.cafebrown.domain.usecase

import com.example.cafebrown.data.models.product.APIGetSubMenuAndProductResponse
import com.example.cafebrown.domain.repository.ProductRepository
import com.example.cafebrown.utils.Resource

class GetSubMenuAndProductListUseCase(private val productRepository: ProductRepository) {
    suspend fun execute(menuId: Int): Resource<APIGetSubMenuAndProductResponse> = productRepository.getSubCategoryAndProductList(menuId)
}