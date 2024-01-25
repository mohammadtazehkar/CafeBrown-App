package com.example.cafebrown.di

import com.example.cafebrown.data.repository.datasource.AppLocalDataSource
import com.example.cafebrown.data.repository.datasource.DeskRemoteDataSource
import com.example.cafebrown.data.repository.datasource.HomeRemoteDataSource
import com.example.cafebrown.data.repository.datasource.LoginRemoteDataSource
import com.example.cafebrown.data.repository.datasource.MenuRemoteDataSource
import com.example.cafebrown.data.repository.datasource.ProductRemoteDataSource
import com.example.cafebrown.data.repository.datasource.ProfileRemoteDataSource
import com.example.cafebrown.data.repository.datasource.ReserveRemoteDataSource
import com.example.cafebrown.data.repository.datasource.TransactionRemoteDataSource
import com.example.cafebrown.data.repository.datasource.VerifyRemoteDataSource
import com.example.cafebrown.data.repository.repositoryImpl.CheckUserDataRepositoryImpl
import com.example.cafebrown.data.repository.repositoryImpl.DeskRepositoryImpl
import com.example.cafebrown.data.repository.repositoryImpl.HomeRepositoryImpl
import com.example.cafebrown.data.repository.repositoryImpl.LoginRepositoryImpl
import com.example.cafebrown.data.repository.repositoryImpl.MenuRepositoryImpl
import com.example.cafebrown.data.repository.repositoryImpl.ProductRepositoryImpl
import com.example.cafebrown.data.repository.repositoryImpl.ProfileRepositoryImpl
import com.example.cafebrown.data.repository.repositoryImpl.ReserveRepositoryImpl
import com.example.cafebrown.data.repository.repositoryImpl.TransactionRepositoryImpl
import com.example.cafebrown.data.repository.repositoryImpl.VerifyRepositoryImpl
import com.example.cafebrown.domain.repository.CheckUserDataRepository
import com.example.cafebrown.domain.repository.DeskRepository
import com.example.cafebrown.domain.repository.HomeRepository
import com.example.cafebrown.domain.repository.LoginRepository
import com.example.cafebrown.domain.repository.MenuRepository
import com.example.cafebrown.domain.repository.ProductRepository
import com.example.cafebrown.domain.repository.ProfileRepository
import com.example.cafebrown.domain.repository.ReserveRepository
import com.example.cafebrown.domain.repository.TransactionRepository
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
    ): CheckUserDataRepository {
        return CheckUserDataRepositoryImpl(
            appLocalDataSource
        )
    }

    @Singleton
    @Provides
    fun provideLoginRepository(
        loginRemoteDataSource: LoginRemoteDataSource,
        networkUtil: NetworkUtil
    ): LoginRepository {
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
    ): VerifyRepository {
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
    ): ProfileRepository {
        return ProfileRepositoryImpl(
            profileRemoteDataSource,
            appLocalDataSource,
            networkUtil
        )
    }

    @Singleton
    @Provides
    fun provideTransactionRepository(
        transactionRemoteDataSource: TransactionRemoteDataSource,
        appLocalDataSource: AppLocalDataSource,
        networkUtil: NetworkUtil
    ): TransactionRepository {
        return TransactionRepositoryImpl(
            transactionRemoteDataSource,
            appLocalDataSource,
            networkUtil
        )
    }

    @Singleton
    @Provides
    fun provideMenuRepository(
        menuRemoteDataSource: MenuRemoteDataSource,
        appLocalDataSource: AppLocalDataSource,
        networkUtil: NetworkUtil
    ): MenuRepository{
        return MenuRepositoryImpl(
            menuRemoteDataSource,
            appLocalDataSource,
            networkUtil
        )
    }

    @Singleton
    @Provides
    fun provideProductRepository(
        productRemoteDataSource: ProductRemoteDataSource,
        appLocalDataSource: AppLocalDataSource,
        networkUtil: NetworkUtil
    ): ProductRepository{
        return ProductRepositoryImpl(
            productRemoteDataSource,
            appLocalDataSource,
            networkUtil
        )
    }

    @Singleton
    @Provides
    fun provideHomeRepository(
        homeRemoteDataSource: HomeRemoteDataSource,
        appLocalDataSource: AppLocalDataSource,
        networkUtil: NetworkUtil
    ): HomeRepository{
        return HomeRepositoryImpl(
            homeRemoteDataSource,
            appLocalDataSource,
            networkUtil
        )
    }
    @Singleton
    @Provides
    fun provideDeskRepository(
        deskRemoteDataSource: DeskRemoteDataSource,
        appLocalDataSource: AppLocalDataSource,
        networkUtil: NetworkUtil
    ): DeskRepository{
        return DeskRepositoryImpl(
            deskRemoteDataSource,
            appLocalDataSource,
            networkUtil
        )
    }
    @Singleton
    @Provides
    fun provideReserveRepository(
        reserveRemoteDataSource: ReserveRemoteDataSource,
        appLocalDataSource: AppLocalDataSource,
        networkUtil: NetworkUtil
    ): ReserveRepository{
        return ReserveRepositoryImpl(
            reserveRemoteDataSource,
            appLocalDataSource,
            networkUtil
        )
    }
}