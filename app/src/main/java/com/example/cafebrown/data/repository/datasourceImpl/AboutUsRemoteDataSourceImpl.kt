package com.example.cafebrown.data.repository.datasourceImpl

import com.example.cafebrown.data.api.APIService
import com.example.cafebrown.data.models.APIGlobalResponse
import com.example.cafebrown.data.models.aboutUs.APIGetCoffeeShopDataResponse
import com.example.cafebrown.data.models.aboutUs.APIPostComplaintsRequest
import com.example.cafebrown.data.repository.datasource.AboutUsRemoteDataSource
import retrofit2.Response

class AboutUsRemoteDataSourceImpl(private val apiService: APIService) : AboutUsRemoteDataSource {
    override suspend fun getCoffeeShopData(token: String): Response<APIGetCoffeeShopDataResponse> {
        return apiService.getCoffeeShopData(token)
    }

    override suspend fun postComplaints(
        token: String,
        apiPostComplaintsRequest: APIPostComplaintsRequest
    ): Response<APIGlobalResponse> {
        return apiService.postComplaints(token, apiPostComplaintsRequest)
    }
}