package com.example.cafebrown.data.repository.datasource

import com.example.cafebrown.data.models.APIGlobalResponse
import com.example.cafebrown.data.models.aboutUs.APIGetCoffeeShopDataResponse
import com.example.cafebrown.data.models.aboutUs.APIPostComplaintsRequest
import com.example.cafebrown.data.models.transaction.APIGetUserTransactionsResponse
import com.example.cafebrown.data.models.transaction.APIPostIncreaseBalanceRequest
import retrofit2.Response

interface AboutUsRemoteDataSource {
    suspend fun getCoffeeShopData(token: String): Response<APIGetCoffeeShopDataResponse>
    suspend fun postComplaints(
        token: String, apiPostComplaintsRequest: APIPostComplaintsRequest
    ): Response<APIGlobalResponse>
}