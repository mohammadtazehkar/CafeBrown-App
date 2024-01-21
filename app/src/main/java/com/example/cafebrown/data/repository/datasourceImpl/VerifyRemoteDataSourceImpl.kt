package com.example.cafebrown.data.repository.datasourceImpl

import com.example.cafebrown.data.api.APIService
import com.example.cafebrown.data.models.verify.APIPostVerificationCodeRequest
import com.example.cafebrown.data.models.verify.APIPostVerificationCodeResponse
import com.example.cafebrown.data.repository.datasource.VerifyRemoteDataSource
import com.example.cafebrown.utils.ServerConstants
import retrofit2.Response

class VerifyRemoteDataSourceImpl(
    private val apiService: APIService,
) : VerifyRemoteDataSource {
    override suspend fun verify(apiPostVerificationCodeRequest: APIPostVerificationCodeRequest): Response<APIPostVerificationCodeResponse> {
        return apiService.postVerificationCode(apiPostVerificationCodeRequest)
    }

}