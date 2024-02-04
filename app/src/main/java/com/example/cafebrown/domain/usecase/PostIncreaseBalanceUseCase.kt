package com.example.cafebrown.domain.usecase

import com.example.cafebrown.data.models.APIGlobalResponse
import com.example.cafebrown.data.models.transaction.APIPostIncreaseBalanceRequest
import com.example.cafebrown.domain.repository.TransactionRepository
import com.example.cafebrown.utils.Resource

class PostIncreaseBalanceUseCase(
    private val transactionRepository: TransactionRepository
) {
    suspend fun execute(increaseBalanceObject: APIPostIncreaseBalanceRequest): Resource<APIGlobalResponse> {
        return transactionRepository.postIncreaseBalance(increaseBalanceObject)
    }
}