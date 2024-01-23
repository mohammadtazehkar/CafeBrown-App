package com.example.cafebrown.data.repository.datasource

import com.example.cafebrown.data.models.verify.PostVerificationCodeResponse


interface AppLocalDataSource {
    suspend fun saveUserInfoToDB(postVerificationCodeResponse: PostVerificationCodeResponse)
    suspend fun getUserInfoFromDB():PostVerificationCodeResponse
    suspend fun checkUserData(): Int
    suspend fun getTokenFromDB(): String
    suspend fun getMobileFromDB(): String
    suspend fun deleteUserInfo()

}