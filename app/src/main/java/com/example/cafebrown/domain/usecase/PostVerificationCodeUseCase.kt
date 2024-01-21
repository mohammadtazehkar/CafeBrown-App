package com.example.cafebrown.domain.usecase

import com.example.cafebrown.data.models.verify.APIPostVerificationCodeResponse
import com.example.cafebrown.domain.repository.VerifyRepository
import com.example.cafebrown.utils.Resource


class PostVerificationCodeUseCase(private val verifyRepository: VerifyRepository) {
    suspend fun execute(mobileNumber: String,verificationCode: String): Resource<APIPostVerificationCodeResponse> = verifyRepository.verify(mobileNumber,verificationCode)

}