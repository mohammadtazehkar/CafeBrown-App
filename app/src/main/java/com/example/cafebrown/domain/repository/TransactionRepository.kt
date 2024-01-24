package com.example.cafebrown.domain.repository

import com.example.cafebrown.data.models.transaction.APIGetUserTransactionsResponse
import com.example.cafebrown.utils.Resource

interface TransactionRepository {
    suspend fun getTransactionList(): Resource<APIGetUserTransactionsResponse>
}