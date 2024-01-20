package com.example.cafebrown.data.repository.repositoryImpl

import com.example.cafebrown.data.repository.datasource.AppLocalDataSource
import com.example.cafebrown.domain.repository.CheckUserExistRepository

class CheckUserExistRepositoryImpl(
    private val appLocalDataSource: AppLocalDataSource
) : CheckUserExistRepository {

//    override suspend fun check(): Boolean {
//        return appLocalDataSource.checkUserExist()
//    }
}