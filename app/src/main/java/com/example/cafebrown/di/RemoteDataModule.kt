package com.example.cafebrown.di

import com.example.cafebrown.data.api.APIService
import com.example.cafebrown.data.repository.datasource.LoginRemoteDataSource
import com.example.cafebrown.data.repository.datasource.ProfileRemoteDataSource
import com.example.cafebrown.data.repository.datasource.VerifyRemoteDataSource
import com.example.cafebrown.data.repository.datasourceImpl.LoginRemoteDataSourceImpl
import com.example.cafebrown.data.repository.datasourceImpl.ProfileRemoteDataSourceImpl
import com.example.cafebrown.data.repository.datasourceImpl.VerifyRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RemoteDataModule {

    @Singleton
    @Provides
    fun provideLoginRemoteDataSource(
        apiService: APIService
    ): LoginRemoteDataSource {
        return LoginRemoteDataSourceImpl(apiService)
    }

    @Singleton
    @Provides
    fun provideVerifyRemoteDataSource(
        apiService: APIService
    ): VerifyRemoteDataSource {
        return VerifyRemoteDataSourceImpl(apiService)
    }

    @Singleton
    @Provides
    fun provideProfileRemoteDataSource(
        apiService: APIService
    ): ProfileRemoteDataSource {
        return ProfileRemoteDataSourceImpl(apiService)
    }
}