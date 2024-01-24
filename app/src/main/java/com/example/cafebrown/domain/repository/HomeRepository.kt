package com.example.cafebrown.domain.repository

import com.example.cafebrown.data.models.home.APIGetHomeDataResponse
import com.example.cafebrown.utils.Resource

interface HomeRepository {
    suspend fun getHomeData(): Resource<APIGetHomeDataResponse>
}