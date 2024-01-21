package com.example.cafebrown.domain.repository

import com.example.cafebrown.data.models.APIGlobalResponse
import com.example.cafebrown.data.models.profile.APIUpdateProfileRequest
import com.example.cafebrown.data.models.verify.PostVerificationCodeResponse
import com.example.cafebrown.utils.Resource

interface ProfileRepository {

    suspend fun getProfileData(): PostVerificationCodeResponse
    suspend fun updateProfileData(apiUpdateProfileRequest: APIUpdateProfileRequest): Resource<APIGlobalResponse>
}