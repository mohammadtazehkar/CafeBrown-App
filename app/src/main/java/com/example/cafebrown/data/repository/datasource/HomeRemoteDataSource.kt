package com.example.cafebrown.data.repository.datasource

import com.example.cafebrown.data.models.home.APIGetHomeDataResponse
import retrofit2.Response

interface HomeRemoteDataSource {
    suspend fun getHomeData(token: String): Response<APIGetHomeDataResponse>
}