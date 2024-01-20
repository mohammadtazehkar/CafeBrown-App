package com.example.cafebrown.di

import android.app.Application
import androidx.room.Room
import com.example.cafebrown.data.db.AppDAO
import com.example.cafebrown.data.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(app,AppDatabase::class.java, name = "cafeBrown_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideAppDao(appDatabase: AppDatabase): AppDAO {
        return appDatabase.getAppDAO()
    }
}