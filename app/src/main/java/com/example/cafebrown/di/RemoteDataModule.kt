package com.example.cafebrown.di

import com.example.cafebrown.data.api.APIService
import com.example.cafebrown.data.repository.datasource.AboutUsRemoteDataSource
import com.example.cafebrown.data.repository.datasource.DeskRemoteDataSource
import com.example.cafebrown.data.repository.datasource.HomeRemoteDataSource
import com.example.cafebrown.data.repository.datasource.LoginRemoteDataSource
import com.example.cafebrown.data.repository.datasource.MenuRemoteDataSource
import com.example.cafebrown.data.repository.datasource.ProductDetailRemoteDataSource
import com.example.cafebrown.data.repository.datasource.ProductRemoteDataSource
import com.example.cafebrown.data.repository.datasource.ProfileRemoteDataSource
import com.example.cafebrown.data.repository.datasource.ReserveHistoryRemoteDataSource
import com.example.cafebrown.data.repository.datasource.ReserveRemoteDataSource
import com.example.cafebrown.data.repository.datasource.TransactionRemoteDataSource
import com.example.cafebrown.data.repository.datasource.VerifyRemoteDataSource
import com.example.cafebrown.data.repository.datasourceImpl.AboutUsRemoteDataSourceImpl
import com.example.cafebrown.data.repository.datasourceImpl.DeskRemoteDataSourceImpl
import com.example.cafebrown.data.repository.datasourceImpl.HomeDataRemoteDataSourceImpl
import com.example.cafebrown.data.repository.datasourceImpl.LoginRemoteDataSourceImpl
import com.example.cafebrown.data.repository.datasourceImpl.MenuRemoteDataSourceImpl
import com.example.cafebrown.data.repository.datasourceImpl.ProductDetailRemoteDataSourceImpl
import com.example.cafebrown.data.repository.datasourceImpl.ProductRemoteDataSourceImpl
import com.example.cafebrown.data.repository.datasourceImpl.ProfileRemoteDataSourceImpl
import com.example.cafebrown.data.repository.datasourceImpl.ReserveHistoryRemoteDataSourceImpl
import com.example.cafebrown.data.repository.datasourceImpl.TransactionRemoteDataSourceImpl
import com.example.cafebrown.data.repository.datasourceImpl.ReserveRemoteDataSourceImpl
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

    @Singleton
    @Provides
    fun provideHomeRemoteDataSource(
        apiService: APIService
    ): HomeRemoteDataSource {
        return HomeDataRemoteDataSourceImpl(apiService)
    }

    @Singleton
    @Provides
    fun provideDeskRemoteDataSource(
        apiService: APIService
    ): DeskRemoteDataSource {
        return DeskRemoteDataSourceImpl(apiService)
    }

    @Singleton
    @Provides
    fun provideReserveRemoteDataSource(
        apiService: APIService
    ): ReserveRemoteDataSource {
        return ReserveRemoteDataSourceImpl(apiService)
    }

    @Singleton
    @Provides
    fun provideMenuRemoteDataSource(
        apiService: APIService
    ): MenuRemoteDataSource {
        return MenuRemoteDataSourceImpl(apiService)
    }

    @Singleton
    @Provides
    fun provideProductRemoteDataSource(
        apiService: APIService
    ): ProductRemoteDataSource {
        return ProductRemoteDataSourceImpl(apiService)
    }

    @Singleton
    @Provides
    fun provideTransactionRemoteDataSource(apiService: APIService): TransactionRemoteDataSource {
        return TransactionRemoteDataSourceImpl(apiService)
    }

    @Singleton
    @Provides
    fun provideAboutUsRemoteDataSource(apiService: APIService): AboutUsRemoteDataSource {
        return AboutUsRemoteDataSourceImpl(apiService)
    }

    @Singleton
    @Provides
    fun provideProductDetailRemoteDataSource(
        apiService: APIService
    ): ProductDetailRemoteDataSource {
        return ProductDetailRemoteDataSourceImpl(apiService)
    }

    @Singleton
    @Provides
    fun provideReserveHistoryRemoteDataSource(
        apiService: APIService
    ): ReserveHistoryRemoteDataSource {
        return ReserveHistoryRemoteDataSourceImpl(apiService)
    }
}