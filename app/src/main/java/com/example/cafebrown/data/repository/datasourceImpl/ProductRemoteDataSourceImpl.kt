package com.example.cafebrown.data.repository.datasourceImpl

import com.example.cafebrown.data.api.APIService
import com.example.cafebrown.data.models.menu.APIGetMenuResponse
import com.example.cafebrown.data.models.product.APIGetSubMenuAndProductResponse
import com.example.cafebrown.data.repository.datasource.ProductRemoteDataSource
import retrofit2.Response

class ProductRemoteDataSourceImpl(
    private val apiService: APIService
):ProductRemoteDataSource {
    override suspend fun getSubMenuAndProductList(
        token: String,
        menuId: Int
    ): Response<APIGetSubMenuAndProductResponse> = apiService.getSubMenuAndProductList(token,menuId)
}