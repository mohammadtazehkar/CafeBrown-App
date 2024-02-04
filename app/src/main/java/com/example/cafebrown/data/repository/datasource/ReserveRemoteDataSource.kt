package com.example.cafebrown.data.repository.datasource

import com.example.cafebrown.data.models.APIGlobalResponse
import com.example.cafebrown.data.models.reserve.APIGetReserveBaseInfoResponse
import com.example.cafebrown.data.models.reserve.APIReserveCheckRequest
import retrofit2.Response

interface ReserveRemoteDataSource {
    suspend fun getReserveTimes(token :String, tableId: Int): Response<APIGetReserveBaseInfoResponse>

    suspend fun postReserveCheck(token :String, apiReserveCheckRequest: APIReserveCheckRequest): Response<APIGlobalResponse>

}