package com.example.cafebrown.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AppDAO {

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertUserToken(signInTokenData: SignInTokenData)
//
//    @Query("SELECT accessToken FROM userToken")
//    suspend fun getUserToken(): String
//
//    @Query("SELECT COUNT(*) FROM userInfo")
//    suspend fun hasUserData(): Int

}