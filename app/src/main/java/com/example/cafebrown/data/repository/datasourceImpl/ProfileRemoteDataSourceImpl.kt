package com.example.cafebrown.data.repository.datasourceImpl

import com.example.cafebrown.data.api.APIService
import com.example.cafebrown.data.models.APIGlobalResponse
import com.example.cafebrown.data.models.profile.APIUpdateProfileRequest
import com.example.cafebrown.data.repository.datasource.ProfileRemoteDataSource
import retrofit2.Response

class ProfileRemoteDataSourceImpl(
    private val apiService: APIService,
) : ProfileRemoteDataSource {

    override suspend fun updateProfileData(token: String, apiUpdateProfileRequest: APIUpdateProfileRequest): Response<APIGlobalResponse> {
        return apiService.updateProfile(token, apiUpdateProfileRequest)
    }

}