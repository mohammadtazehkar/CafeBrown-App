package com.example.cafebrown.domain.usecase

import com.example.cafebrown.data.models.APIGlobalResponse
import com.example.cafebrown.data.models.aboutUs.APIPostComplaintsRequest
import com.example.cafebrown.data.models.transaction.APIPostIncreaseBalanceRequest
import com.example.cafebrown.domain.repository.AboutUsRepository
import com.example.cafebrown.utils.Resource

class PostComplaintsUseCase(private val aboutUsRepository: AboutUsRepository) {
    suspend fun execute(apiPostComplaintsRequest: APIPostComplaintsRequest): Resource<APIGlobalResponse> =
        aboutUsRepository.postComplaints(apiPostComplaintsRequest)

}