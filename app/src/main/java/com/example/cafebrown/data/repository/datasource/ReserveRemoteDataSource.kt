package com.example.cafebrown.data.repository.datasource

import com.example.cafebrown.data.models.reserve.APIGetReserveBaseInfoResponse
import retrofit2.Response

interface ReserveRemoteDataSource {
    suspend fun getReserveTimes(token :String, tableId: Int): Response<APIGetReserveBaseInfoResponse>
}