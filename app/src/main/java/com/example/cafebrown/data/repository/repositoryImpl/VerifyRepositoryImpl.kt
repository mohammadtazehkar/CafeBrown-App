package com.example.cafebrown.data.repository.repositoryImpl

import com.example.cafebrown.data.models.verify.APIPostVerificationCodeRequest
import com.example.cafebrown.data.models.verify.APIPostVerificationCodeResponse
import com.example.cafebrown.data.repository.datasource.AppLocalDataSource
import com.example.cafebrown.data.repository.datasource.VerifyRemoteDataSource
import com.example.cafebrown.domain.repository.VerifyRepository
import com.example.cafebrown.utils.JSonStatusCode.INTERNET_CONNECTION
import com.example.cafebrown.utils.JSonStatusCode.SERVER_CONNECTION
import com.example.cafebrown.utils.JSonStatusCode.SUCCESS
import com.example.cafebrown.utils.NetworkUtil
import com.example.cafebrown.utils.Resource

class VerifyRepositoryImpl (
    private val verifyRemoteDataSource: VerifyRemoteDataSource,
    private val appLocalDataSource: AppLocalDataSource,
    private val networkUtil: NetworkUtil
): VerifyRepository {
    override suspend fun verify(
        mobileNumber: String,
        verificationCode: String
    ): Resource<APIPostVerificationCodeResponse> {
        return if (networkUtil.isInternetAvailable()) {
            try {
                val response = verifyRemoteDataSource.verify(APIPostVerificationCodeRequest(mobileNumber = mobileNumber, verificationCode = "123456"))
                if (response.isSuccessful && response.body() != null) {
                    if (response.body()?.status == SUCCESS){
                        appLocalDataSource.saveUserInfoToDB(response.body()?.data!!)
                    }
                    Resource.Success(response.body()!!)
                } else {
                    Resource.Error("An error occurred",APIPostVerificationCodeResponse(SERVER_CONNECTION,null,""))
                }
            } catch (e: Exception) {
                Resource.Error(e.message ?: "An error occurred",APIPostVerificationCodeResponse(SERVER_CONNECTION,null,""))
            }
        } else {
            Resource.Error("No internet connection", APIPostVerificationCodeResponse(INTERNET_CONNECTION, null,""))
        }
    }
}