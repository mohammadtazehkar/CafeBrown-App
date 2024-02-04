package com.example.cafebrown.data.repository.datasourceImpl

import com.example.cafebrown.data.api.APIService
import com.example.cafebrown.data.models.APIGlobalResponse
import com.example.cafebrown.data.models.reserve.APIGetReserveBaseInfoResponse
import com.example.cafebrown.data.models.reserve.APIReserveCheckRequest
import com.example.cafebrown.data.repository.datasource.ReserveRemoteDataSource
import retrofit2.Response

class ReserveRemoteDataSourceImpl(private val apiService: APIService):ReserveRemoteDataSource {
    override suspend fun getReserveTimes(
        token: String,
        tableId: Int
    ): Response<APIGetReserveBaseInfoResponse> {
        return apiService.getReserveTimes(token,tableId)
    }

    override suspend fun postReserveCheck(
        token: String,
        apiReserveCheckRequest: APIReserveCheckRequest
    ): Response<APIGlobalResponse> {
        return apiService.postReserveCheck(token, apiReserveCheckRequest)
    }
}