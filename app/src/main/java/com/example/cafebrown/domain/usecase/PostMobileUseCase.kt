package com.example.cafebrown.domain.usecase

import com.example.cafebrown.data.models.APIGlobalResponse
import com.example.cafebrown.domain.repository.LoginRepository
import com.example.cafebrown.utils.Resource


class PostMobileUseCase(private val loginRepository: LoginRepository) {
    suspend fun execute(mobileNumber: String): Resource<APIGlobalResponse> = loginRepository.postMobile(mobileNumber)

}