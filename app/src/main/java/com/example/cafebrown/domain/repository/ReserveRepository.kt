package com.example.cafebrown.domain.repository

import com.example.cafebrown.data.models.reserve.APIGetReserveBaseInfoResponse
import com.example.cafebrown.utils.Resource

interface ReserveRepository {
    suspend fun getReserveTimes(tableId: Int) : Resource<APIGetReserveBaseInfoResponse>
}