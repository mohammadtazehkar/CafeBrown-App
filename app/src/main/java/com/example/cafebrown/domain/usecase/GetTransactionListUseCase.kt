package com.example.cafebrown.domain.usecase

import com.example.cafebrown.data.models.transaction.APIGetUserTransactionsResponse
import com.example.cafebrown.domain.repository.TransactionRepository
import com.example.cafebrown.utils.Resource

class GetTransactionListUseCase(private val transactionRepository: TransactionRepository) {
    suspend fun execute(): Resource<APIGetUserTransactionsResponse> =
        transactionRepository.getTransactionList()

}