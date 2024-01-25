package com.example.cafebrown.domain.usecase

import com.example.cafebrown.data.models.home.APIGetHomeDataResponse
import com.example.cafebrown.domain.repository.HomeRepository
import com.example.cafebrown.utils.Resource

class GetHomeDataUseCase(private val homeRepository: HomeRepository) {
    suspend fun execute(): Resource<APIGetHomeDataResponse> = homeRepository.getHomeData()
}