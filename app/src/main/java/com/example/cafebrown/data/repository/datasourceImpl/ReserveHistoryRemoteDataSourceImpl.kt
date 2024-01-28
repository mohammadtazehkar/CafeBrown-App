package com.example.cafebrown.data.repository.datasourceImpl

import com.example.cafebrown.data.api.APIService
import com.example.cafebrown.data.models.reserveHistory.APIGetUserReserveResponse
import com.example.cafebrown.data.repository.datasource.ReserveHistoryRemoteDataSource
import retrofit2.Response

class ReserveHistoryRemoteDataSourceImpl(
    private val apiService: APIService
): ReserveHistoryRemoteDataSource {
    override suspend fun getReserveHistoryList(token: String): Response<APIGetUserReserveResponse> = apiService.getUserReserves(token)
}