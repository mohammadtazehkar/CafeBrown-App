package com.example.cafebrown.data.repository.repositoryImpl

import com.example.cafebrown.data.models.APIGlobalResponse
import com.example.cafebrown.data.repository.datasource.LoginRemoteDataSource
import com.example.cafebrown.domain.repository.LoginRepository
import com.example.cafebrown.utils.JSonStatusCode.INTERNET_CONNECTION
import com.example.cafebrown.utils.JSonStatusCode.SERVER_CONNECTION
import com.example.cafebrown.utils.NetworkUtil
import com.example.cafebrown.utils.Resource

class LoginRepositoryImpl (
    private val loginRemoteDataSource: LoginRemoteDataSource,
    private val networkUtil: NetworkUtil
): LoginRepository {

    override suspend fun postMobile(mobileNumber: String): Resource<APIGlobalResponse> {
        return if (networkUtil.isInternetAvailable()) {
            try {
                val response = loginRemoteDataSource.postMobile(mobileNumber)
                if (response.isSuccessful && response.body() != null) {
                    Resource.Success(response.body()!!)
                } else {
                    Resource.Error("An error occurred",APIGlobalResponse(SERVER_CONNECTION,"An error occurred",null))
                }
            } catch (e: Exception) {
                Resource.Error(e.message ?: "An error occurred",APIGlobalResponse(SERVER_CONNECTION,"An error occurred",null))
            }
        } else {
            Resource.Error("No internet connection", APIGlobalResponse(INTERNET_CONNECTION, "No internet connection",null))
        }
    }
}