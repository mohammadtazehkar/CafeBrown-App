package com.example.cafebrown.domain.repository

import com.example.cafebrown.data.models.APIGlobalResponse
import com.example.cafebrown.data.models.profile.APIUpdateProfileRequest
import com.example.cafebrown.data.models.reserve.APIGetReserveBaseInfoResponse
import com.example.cafebrown.data.models.reserve.APIReserveCheckRequest
import com.example.cafebrown.utils.Resource

interface ReserveRepository {
    suspend fun getReserveTimes(tableId: Int) : Resource<APIGetReserveBaseInfoResponse>
    suspend fun postReserveCheck(apiReserveCheckRequest: APIReserveCheckRequest): Resource<APIGlobalResponse>
}