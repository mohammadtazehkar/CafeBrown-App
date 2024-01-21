package com.example.cafebrown.data.repository.repositoryImpl

import com.example.cafebrown.data.repository.datasource.AppLocalDataSource
import com.example.cafebrown.domain.repository.CheckUserDataRepository

class CheckUserDataRepositoryImpl(
    private val appLocalDataSource: AppLocalDataSource
) : CheckUserDataRepository {
    override suspend fun check(): Int {
        return appLocalDataSource.checkUserData()
    }
}