package com.example.cafebrown.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cafebrown.data.models.PostVerificationCodeResponse

@Database(
    entities = [PostVerificationCodeResponse::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getAppDAO():AppDAO
}