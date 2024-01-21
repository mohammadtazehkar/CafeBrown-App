package com.example.cafebrown.data.repository.datasource

import com.example.cafebrown.data.models.APIGlobalResponse
import retrofit2.Response

interface LoginRemoteDataSource {
    suspend fun postMobile(mobileNumber: String): Response<APIGlobalResponse>
}