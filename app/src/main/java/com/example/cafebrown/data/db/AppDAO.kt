package com.example.cafebrown.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cafebrown.data.models.verify.PostVerificationCodeResponse

@Dao
interface AppDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserInfo(postVerificationCodeResponse: PostVerificationCodeResponse)

    @Query("SELECT * FROM userInfo")
    suspend fun getUserInfo(): PostVerificationCodeResponse

    @Query("SELECT COUNT(*) FROM userInfo")
    suspend fun hasUserData(): Int

    @Query("SELECT firstName FROM userInfo")
    suspend fun getUserFirstName(): String

    @Query("SELECT accessToken FROM userInfo")
    suspend fun getUserAccessToken(): String

    @Query("SELECT mobile FROM userInfo")
    suspend fun getUserMobile(): String

    @Query("DELETE FROM userInfo")
    suspend fun deleteUserInfo()

}