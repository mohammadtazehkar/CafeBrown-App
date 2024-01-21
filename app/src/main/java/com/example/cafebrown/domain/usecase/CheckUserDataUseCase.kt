package com.example.cafebrown.domain.usecase

import com.example.cafebrown.domain.repository.CheckUserDataRepository


class CheckUserDataUseCase(private val checkUserDataRepository: CheckUserDataRepository) {
    suspend fun execute(): Int = checkUserDataRepository.check()

}