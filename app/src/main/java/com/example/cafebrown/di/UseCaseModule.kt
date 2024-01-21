package com.example.cafebrown.di

import com.example.cafebrown.domain.repository.CheckUserDataRepository
import com.example.cafebrown.domain.repository.LoginRepository
import com.example.cafebrown.domain.repository.ProfileRepository
import com.example.cafebrown.domain.repository.VerifyRepository
import com.example.cafebrown.domain.usecase.CheckUserDataUseCase
import com.example.cafebrown.domain.usecase.GetProfileDataUseCase
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
    ):CheckUserDataUseCase{
        return CheckUserDataUseCase(checkUserDataRepository)
    }
    @Singleton
    @Provides
    fun providePostMobileUseCase(
        loginRepository: LoginRepository
    ):PostMobileUseCase{
        return PostMobileUseCase(loginRepository)
    }

    @Singleton
    @Provides
    fun providePostVerificationCodeUseCase(
        verifyRepository: VerifyRepository
    ):PostVerificationCodeUseCase{
        return PostVerificationCodeUseCase(verifyRepository)
    }

    @Singleton
    @Provides
    fun provideGetProfileDataUseCase(
        profileRepository: ProfileRepository
    ):GetProfileDataUseCase{
        return GetProfileDataUseCase(profileRepository)
    }

    @Singleton
    @Provides
    fun provideUpdateProfileDataUseCase(
        profileRepository: ProfileRepository
    ):UpdateProfileDataUseCase{
        return UpdateProfileDataUseCase(profileRepository)
    }

}