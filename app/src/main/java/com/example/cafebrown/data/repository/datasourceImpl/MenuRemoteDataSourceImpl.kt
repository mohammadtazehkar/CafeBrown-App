package com.example.cafebrown.data.repository.datasourceImpl

import com.example.cafebrown.data.api.APIService
import com.example.cafebrown.data.models.menu.APIGetMenuResponse
import com.example.cafebrown.data.repository.datasource.MenuRemoteDataSource
import retrofit2.Response

class MenuRemoteDataSourceImpl(
    private val apiService: APIService,
) : MenuRemoteDataSource {
    override suspend fun getMenuList(token: String): Response<APIGetMenuResponse> = apiService.getMenuList(token)

}