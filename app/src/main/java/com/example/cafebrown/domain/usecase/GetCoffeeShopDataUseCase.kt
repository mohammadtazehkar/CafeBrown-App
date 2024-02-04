package com.example.cafebrown.domain.usecase

import com.example.cafebrown.data.models.aboutUs.APIGetCoffeeShopDataResponse
import com.example.cafebrown.domain.repository.AboutUsRepository
import com.example.cafebrown.utils.Resource

class GetCoffeeShopDataUseCase(private val aboutUsRepository: AboutUsRepository) {
    suspend fun execute(): Resource<APIGetCoffeeShopDataResponse> =
        aboutUsRepository.getCoffeeShopData()
}