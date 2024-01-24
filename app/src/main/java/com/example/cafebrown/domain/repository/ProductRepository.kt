package com.example.cafebrown.domain.repository

import com.example.cafebrown.data.models.product.APIGetSubMenuAndProductResponse
import com.example.cafebrown.utils.Resource

interface ProductRepository {
    suspend fun getSubCategoryAndProductList(menuId: Int): Resource<APIGetSubMenuAndProductResponse>
}