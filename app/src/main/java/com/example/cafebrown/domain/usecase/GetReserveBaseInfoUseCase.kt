package com.example.cafebrown.domain.usecase

import com.example.cafebrown.data.models.reserve.APIGetReserveBaseInfoResponse
import com.example.cafebrown.domain.repository.ReserveRepository
import com.example.cafebrown.utils.Resource

class GetReserveBaseInfoUseCase(private val reserveRepository: ReserveRepository) {
    suspend fun execute(tableId : Int) : Resource<APIGetReserveBaseInfoResponse> = reserveRepository.getReserveTimes(tableId)
}