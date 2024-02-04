package com.example.cafebrown.domain.usecase

import com.example.cafebrown.data.models.APIGlobalResponse
import com.example.cafebrown.data.models.reserve.APIReserveCheckRequest
import com.example.cafebrown.data.models.verify.APIPostVerificationCodeResponse
import com.example.cafebrown.domain.repository.ReserveRepository
import com.example.cafebrown.utils.Resource

class PostReserveCheckUseCase(private val reserveRepository: ReserveRepository) {
    suspend fun execute(apiReserveCheckRequest: APIReserveCheckRequest): Resource<APIGlobalResponse> = reserveRepository.postReserveCheck(apiReserveCheckRequest)

}