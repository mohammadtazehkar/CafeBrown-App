package com.example.cafebrown.data.repository.datasource

import com.example.cafebrown.data.models.reserveHistory.APIGetUserReserveResponse
import retrofit2.Response

interface ReserveHistoryRemoteDataSource {
    suspend fun getReserveHistoryList(token: String): Response<APIGetUserReserveResponse>

}