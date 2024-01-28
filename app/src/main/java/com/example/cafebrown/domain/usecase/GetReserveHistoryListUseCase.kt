package com.example.cafebrown.domain.usecase

import com.example.cafebrown.data.models.reserveHistory.APIGetUserReserveResponse
import com.example.cafebrown.domain.repository.ReserveHistoryRepository
import com.example.cafebrown.utils.Resource

class GetReserveHistoryListUseCase(private val reserveHistoryRepository: ReserveHistoryRepository) {
    suspend fun execute(): Resource<APIGetUserReserveResponse> = reserveHistoryRepository.getReserveHistoryList()
}