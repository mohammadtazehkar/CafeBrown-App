package com.example.cafebrown.domain.usecase

import com.example.cafebrown.data.models.verify.PostVerificationCodeResponse
import com.example.cafebrown.domain.repository.ProfileRepository

class GetProfileDataUseCase(private val profileRepository: ProfileRepository) {
    suspend fun execute(): PostVerificationCodeResponse = profileRepository.getProfileData()

}