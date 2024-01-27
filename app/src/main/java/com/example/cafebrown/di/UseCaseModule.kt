package com.example.cafebrown.di

import com.example.cafebrown.domain.repository.CheckUserDataRepository
import com.example.cafebrown.domain.repository.DeskRepository
import com.example.cafebrown.domain.repository.HomeRepository
import com.example.cafebrown.domain.repository.LoginRepository
import com.example.cafebrown.domain.repository.MenuRepository
import com.example.cafebrown.domain.repository.ProductDetailRepository
import com.example.cafebrown.domain.repository.ProductRepository
import com.example.cafebrown.domain.repository.ProfileRepository
import com.example.cafebrown.domain.repository.ReserveRepository
import com.example.cafebrown.domain.repository.TransactionRepository
import com.example.cafebrown.domain.repository.VerifyRepository
import com.example.cafebrown.domain.usecase.CheckUserDataUseCase
import com.example.cafebrown.domain.usecase.GetCommentListUseCase
import com.example.cafebrown.domain.usecase.GetMenuListUseCase
import com.example.cafebrown.domain.usecase.GetDeskListDataUseCase
import com.example.cafebrown.domain.usecase.GetHomeDataUseCase
import com.example.cafebrown.domain.usecase.GetProductDetailDataUseCase
import com.example.cafebrown.domain.usecase.GetProfileDataUseCase
import com.example.cafebrown.domain.usecase.GetSubMenuAndProductListUseCase
import com.example.cafebrown.domain.usecase.GetReserveBaseInfoUseCase
import com.example.cafebrown.domain.usecase.GetTransactionListUseCase
import com.example.cafebrown.domain.usecase.PostMobileUseCase
import com.example.cafebrown.domain.usecase.PostVerificationCodeUseCase
import com.example.cafebrown.domain.usecase.UpdateProfileDataUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun provideCheckUserDataUseCase(
        checkUserDataRepository: CheckUserDataRepository
    ): CheckUserDataUseCase {
        return CheckUserDataUseCase(checkUserDataRepository)
    }

    @Singleton
    @Provides
    fun providePostMobileUseCase(
        loginRepository: LoginRepository
    ): PostMobileUseCase {
        return PostMobileUseCase(loginRepository)
    }

    @Singleton
    @Provides
    fun providePostVerificationCodeUseCase(
        verifyRepository: VerifyRepository
    ): PostVerificationCodeUseCase {
        return PostVerificationCodeUseCase(verifyRepository)
    }

    @Singleton
    @Provides
    fun provideGetProfileDataUseCase(
        profileRepository: ProfileRepository
    ): GetProfileDataUseCase {
        return GetProfileDataUseCase(profileRepository)
    }

    @Singleton
    @Provides
    fun provideUpdateProfileDataUseCase(
        profileRepository: ProfileRepository
    ): UpdateProfileDataUseCase {
        return UpdateProfileDataUseCase(profileRepository)
    }

    @Singleton
    @Provides
    fun provideGetHomeDataUseCase(
        homeRepository: HomeRepository
    ): GetHomeDataUseCase {
        return GetHomeDataUseCase(homeRepository)
    }

    @Singleton
    @Provides
    fun provideGetDeskListUserCase(
        deskRepository: DeskRepository
    ): GetDeskListDataUseCase {
        return GetDeskListDataUseCase(deskRepository)
    }

    @Singleton
    @Provides
    fun provideGetReserveBaseInfoUseCase(
        reserveRepository: ReserveRepository
    ): GetReserveBaseInfoUseCase {
        return GetReserveBaseInfoUseCase(reserveRepository)
    }
    @Singleton
    @Provides
    fun provideGetMenuListUseCase(
        menuRepository: MenuRepository
    ):GetMenuListUseCase{
        return GetMenuListUseCase(menuRepository)
    }

    @Singleton
    @Provides
    fun provideGetSubMenuAndProductListUseCase(
        productRepository: ProductRepository
    ):GetSubMenuAndProductListUseCase{
        return GetSubMenuAndProductListUseCase(productRepository)
    }

    @Singleton
    @Provides
    fun provideGetTransactionListUseCase(
        transactionRepository: TransactionRepository
    ): GetTransactionListUseCase {
        return GetTransactionListUseCase(transactionRepository)
    }

    @Singleton
    @Provides
    fun provideGetProductDetailDataUseCase(
        productDetailRepository: ProductDetailRepository
    ): GetProductDetailDataUseCase {
        return GetProductDetailDataUseCase(productDetailRepository)
    }

    @Singleton
    @Provides
    fun provideGetCommentListUseCase(
        productDetailRepository: ProductDetailRepository
    ): GetCommentListUseCase {
        return GetCommentListUseCase(productDetailRepository)
    }

}