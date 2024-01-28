package com.example.cafebrown.domain.repository

import com.example.cafebrown.data.models.reserveHistory.APIGetUserReserveResponse
import com.example.cafebrown.utils.Resource

interface ReserveHistoryRepository {
    suspend fun getReserveHistoryList(): Resource<APIGetUserReserveResponse>
}