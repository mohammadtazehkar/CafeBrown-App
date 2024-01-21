package com.example.cafebrown.data.repository.datasource

import com.example.cafebrown.data.models.verify.APIPostVerificationCodeRequest
import com.example.cafebrown.data.models.verify.APIPostVerificationCodeResponse
import retrofit2.Response

interface VerifyRemoteDataSource {
    suspend fun verify(apiPostVerificationCodeRequest: APIPostVerificationCodeRequest): Response<APIPostVerificationCodeResponse>
}