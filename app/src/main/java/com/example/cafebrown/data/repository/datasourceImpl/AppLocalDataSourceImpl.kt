package com.example.cafebrown.data.repository.datasourceImpl

import android.util.Log
import com.example.cafebrown.data.db.AppDAO
import com.example.cafebrown.data.models.verify.PostVerificationCodeResponse
import com.example.cafebrown.data.repository.datasource.AppLocalDataSource
import com.example.cafebrown.utils.Constants.USER_DATA_EMPTY
import com.example.cafebrown.utils.Constants.USER_DATA_FULL
import com.example.cafebrown.utils.Constants.USER_DATA_TOKEN

class AppLocalDataSourceImpl(
    private val appDAO: AppDAO
): AppLocalDataSource {
    override suspend fun saveUserInfoToDB(postVerificationCodeResponse: PostVerificationCodeResponse) {
        appDAO.insertUserInfo(postVerificationCodeResponse)

    }

    override suspend fun getUserInfoFromDB(): PostVerificationCodeResponse {
        return appDAO.getUserInfo()
    }

    override suspend fun checkUserData(): Int {
        var result : Int = USER_DATA_EMPTY
        if (appDAO.hasUserData() > 0){
            if (appDAO.getUserFirstName().isNotEmpty()){
                result = USER_DATA_FULL
            }else if (appDAO.getUserAccessToken().isNotEmpty()){
                result = USER_DATA_TOKEN
            }
        }
        return result
    }

    override suspend fun getTokenFromDB(): String {
        return appDAO.getUserAccessToken()
    }

    override suspend fun getMobileFromDB(): String {
        return appDAO.getUserMobile()
    }

    override suspend fun deleteUserInfo() {
        appDAO.deleteUserInfo()
    }

}