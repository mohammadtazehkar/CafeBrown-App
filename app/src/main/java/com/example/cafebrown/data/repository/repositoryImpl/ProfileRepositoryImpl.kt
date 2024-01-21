package com.example.cafebrown.data.repository.repositoryImpl

import com.example.cafebrown.data.models.APIGlobalResponse
import com.example.cafebrown.data.models.profile.APIUpdateProfileRequest
import com.example.cafebrown.data.models.verify.PostVerificationCodeResponse
import com.example.cafebrown.data.repository.datasource.AppLocalDataSource
import com.example.cafebrown.data.repository.datasource.ProfileRemoteDataSource
import com.example.cafebrown.domain.repository.ProfileRepository
import com.example.cafebrown.utils.JSonStatusCode
import com.example.cafebrown.utils.JSonStatusCode.INTERNET_CONNECTION
import com.example.cafebrown.utils.JSonStatusCode.SERVER_CONNECTION
import com.example.cafebrown.utils.NetworkUtil
import com.example.cafebrown.utils.Resource
import com.example.cafebrown.utils.ServerConstants.TOKEN_TYPE

class ProfileRepositoryImpl(
    private val profileRemoteDataSource: ProfileRemoteDataSource,
    private val appLocalDataSource: AppLocalDataSource,
    private val networkUtil: NetworkUtil
) : ProfileRepository {
    override suspend fun getProfileData(): PostVerificationCodeResponse {
        return appLocalDataSource.getUserInfoFromDB()
    }

    override suspend fun updateProfileData(apiUpdateProfileRequest: APIUpdateProfileRequest): Resource<APIGlobalResponse> {
        return if (networkUtil.isInternetAvailable()) {
            try {
                val token = appLocalDataSource.getTokenFromDB()
                val mobile = appLocalDataSource.getMobileFromDB()
                val response = profileRemoteDataSource.updateProfileData(
                    token = "$TOKEN_TYPE $token",
                    apiUpdateProfileRequest = apiUpdateProfileRequest
                )
                if (response.isSuccessful && response.body() != null) {
                    if (response.body()?.status == JSonStatusCode.SUCCESS) {
                        appLocalDataSource.saveUserInfoToDB(
                            PostVerificationCodeResponse(
                                0,
                                apiUpdateProfileRequest.firstName,
                                apiUpdateProfileRequest.lastName,
                                apiUpdateProfileRequest.birthDate,
                                apiUpdateProfileRequest.sex,
                                mobile,
                                token
                            )
                        )
                    }
                    Resource.Success(response.body()!!)
                } else {
                    Resource.Error(
                        "An error occurred",
                        APIGlobalResponse(SERVER_CONNECTION, "An error occurred", null)
                    )
                }
            } catch (e: Exception) {
                Resource.Error(
                    e.message ?: "An error occurred",
                    APIGlobalResponse(SERVER_CONNECTION, "An error occurred", null)
                )
            }
        } else {
            Resource.Error(
                "No internet connection",
                APIGlobalResponse(INTERNET_CONNECTION, "No internet connection", null)
            )
        }
    }
}