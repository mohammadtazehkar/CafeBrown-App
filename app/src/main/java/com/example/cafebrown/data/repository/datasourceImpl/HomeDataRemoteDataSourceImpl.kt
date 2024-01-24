package com.example.cafebrown.data.repository.datasourceImpl

import com.example.cafebrown.data.api.APIService
import com.example.cafebrown.data.models.home.APIGetHomeDataResponse
import com.example.cafebrown.data.repository.datasource.HomeRemoteDataSource
import retrofit2.Response

class HomeDataRemoteDataSourceImpl(
    private val apiService: APIService
):HomeRemoteDataSource {
    override suspend fun getHomeData(token: String): Response<APIGetHomeDataResponse> {
        return apiService.getHomeData(token)
    }
}