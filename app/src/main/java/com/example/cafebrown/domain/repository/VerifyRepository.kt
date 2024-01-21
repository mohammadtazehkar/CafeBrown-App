package com.example.cafebrown.domain.repository

import com.example.cafebrown.data.models.verify.APIPostVerificationCodeResponse
import com.example.cafebrown.utils.Resource

interface VerifyRepository {

    suspend fun verify(mobileNumber: String,verificationCode: String): Resource<APIPostVerificationCodeResponse>
}