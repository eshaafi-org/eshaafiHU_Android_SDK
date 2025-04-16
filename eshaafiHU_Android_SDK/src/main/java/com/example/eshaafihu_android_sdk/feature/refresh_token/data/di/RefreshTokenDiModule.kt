package com.example.eshaafihu_android_sdk.feature.refresh_token.data.di

import com.example.eshaafihu_android_sdk.feature.refresh_token.data.apiService.RefreshTokenApi
import com.example.eshaafihu_android_sdk.feature.refresh_token.data.repository.RefreshTokenRepositoryImp
import com.example.eshaafihu_android_sdk.feature.refresh_token.data.usecases.RefreshTokenUseCasesImpl
import com.example.eshaafihu_android_sdk.feature.refresh_token.domain.repository.RefreshTokenRepository
import com.example.eshaafihu_android_sdk.feature.refresh_token.domain.usecases.RefreshTokenUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
internal object RefreshTokenDiModule {

    @Provides
    @Singleton
    fun provideRefreshTokenRepository(
        apiService: RefreshTokenApi
    ): RefreshTokenRepository {
        return RefreshTokenRepositoryImp(apiService)
    }

    @Provides
    @Singleton
    fun provideRefreshTokenUseCase(
        repository: RefreshTokenRepository
    ): RefreshTokenUseCase {
        return RefreshTokenUseCasesImpl(repository)
    }

    @Provides
    @Singleton
    fun provideRefreshTokenApiService(retrofit: Retrofit): RefreshTokenApi {
        return retrofit.create(RefreshTokenApi::class.java)
    }

}
