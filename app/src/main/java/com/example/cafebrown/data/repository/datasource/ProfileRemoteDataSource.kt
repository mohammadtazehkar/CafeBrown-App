package com.example.cafebrown.data.repository.datasource

import com.example.cafebrown.data.models.APIGlobalResponse
import com.example.cafebrown.data.models.profile.APIUpdateProfileRequest
import retrofit2.Response

interface ProfileRemoteDataSource {
    suspend fun updateProfileData(token: String, apiUpdateProfileRequest: APIUpdateProfileRequest): Response<APIGlobalResponse>
}