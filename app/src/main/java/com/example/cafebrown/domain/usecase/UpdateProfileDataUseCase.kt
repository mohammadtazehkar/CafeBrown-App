package com.example.cafebrown.domain.usecase

import com.example.cafebrown.data.models.APIGlobalResponse
import com.example.cafebrown.data.models.profile.APIUpdateProfileRequest
import com.example.cafebrown.domain.repository.ProfileRepository
import com.example.cafebrown.utils.Resource

class UpdateProfileDataUseCase(private val profileRepository: ProfileRepository) {
    suspend fun execute(apiUpdateProfileRequest: APIUpdateProfileRequest): Resource<APIGlobalResponse> = profileRepository.updateProfileData(apiUpdateProfileRequest)

}