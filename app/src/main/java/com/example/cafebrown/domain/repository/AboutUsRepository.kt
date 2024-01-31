package com.example.cafebrown.domain.repository

import com.example.cafebrown.data.models.APIGlobalResponse
import com.example.cafebrown.data.models.aboutUs.APIGetCoffeeShopDataResponse
import com.example.cafebrown.data.models.aboutUs.APIPostComplaintsRequest
import com.example.cafebrown.utils.Resource

interface AboutUsRepository {
    suspend fun getCoffeeShopData(): Resource<APIGetCoffeeShopDataResponse>
    suspend fun postComplaints(apiPostComplaintsRequest: APIPostComplaintsRequest): Resource<APIGlobalResponse>
}