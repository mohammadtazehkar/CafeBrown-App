package com.example.cafebrown.data.repository.datasource

import com.example.cafebrown.data.models.product.APIGetSubMenuAndProductResponse
import retrofit2.Response

interface ProductRemoteDataSource {
    suspend fun getSubMenuAndProductList(token: String,menuId: Int): Response<APIGetSubMenuAndProductResponse>

}