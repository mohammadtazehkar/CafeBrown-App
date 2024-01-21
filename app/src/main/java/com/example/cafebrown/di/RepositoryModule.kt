package com.example.cafebrown.di

import com.example.cafebrown.data.repository.datasource.AppLocalDataSource
import com.example.cafebrown.data.repository.datasource.LoginRemoteDataSource
import com.example.cafebrown.data.repository.datasource.ProfileRemoteDataSource
import com.example.cafebrown.data.repository.datasource.VerifyRemoteDataSource
import com.example.cafebrown.data.repository.repositoryImpl.CheckUserDataRepositoryImpl
import com.example.cafebrown.data.repository.repositoryImpl.LoginRepositoryImpl
import com.example.cafebrown.data.repository.repositoryImpl.ProfileRepositoryImpl
import com.example.cafebrown.data.repository.repositoryImpl.VerifyRepositoryImpl
import com.example.cafebrown.domain.repository.CheckUserDataRepository
import com.example.cafebrown.domain.repository.LoginRepository
import com.example.cafebrown.domain.repository.ProfileRepository
import com.example.cafebrown.domain.repository.VerifyRepository
import com.example.cafebrown.utils.NetworkUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideCheckUserDataRepository(
        appLocalDataSource: AppLocalDataSource
    ): CheckUserDataRepository{
        return CheckUserDataRepositoryImpl(
            appLocalDataSource
        )
    }
    @Singleton
    @Provides
    fun provideLoginRepository(
        loginRemoteDataSource: LoginRemoteDataSource,
        networkUtil: NetworkUtil
    ): LoginRepository{
        return LoginRepositoryImpl(
            loginRemoteDataSource,
            networkUtil
        )
    }

    @Singleton
    @Provides
    fun provideVerifyRepository(
        verifyRemoteDataSource: VerifyRemoteDataSource,
        appLocalDataSource: AppLocalDataSource,
        networkUtil: NetworkUtil
    ): VerifyRepository{
        return VerifyRepositoryImpl(
            verifyRemoteDataSource,
            appLocalDataSource,
            networkUtil
        )
    }

    @Singleton
    @Provides
    fun provideProfileRepository(
        profileRemoteDataSource: ProfileRemoteDataSource,
        appLocalDataSource: AppLocalDataSource,
        networkUtil: NetworkUtil
    ): ProfileRepository{
        return ProfileRepositoryImpl(
            profileRemoteDataSource,
            appLocalDataSource,
            networkUtil
        )
    }
}