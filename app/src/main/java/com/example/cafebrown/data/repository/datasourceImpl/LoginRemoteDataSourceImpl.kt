package com.example.cafebrown.data.repository.datasourceImpl

import com.example.cafebrown.data.api.APIService
import com.example.cafebrown.data.models.APIGlobalResponse
import com.example.cafebrown.data.repository.datasource.LoginRemoteDataSource
import com.example.cafebrown.utils.ServerConstants
import retrofit2.Response

class LoginRemoteDataSourceImpl(
    private val apiService: APIService,
) : LoginRemoteDataSource {
    override suspend fun postMobile(mobileNumber: String): Response<APIGlobalResponse> {
        return apiService.postMobileNumber(ServerConstants.BASE_URL + ServerConstants.SUB_URL_POST_MOBILE + "/" + mobileNumber)
    }

}