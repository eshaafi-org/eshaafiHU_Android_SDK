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


/**
 * Dependency Injection module for handling refresh token operations.
 *
 * This module provides the necessary dependencies for refreshing the authentication token,
 * including the repository, use case, and API service.
 */

@Module
@InstallIn(SingletonComponent::class)
internal object RefreshTokenDiModule {

    /**
     * Provides a singleton instance of the [RefreshTokenRepository] implementation.
     *
     * This method is used by Dagger Hilt to inject the [RefreshTokenRepository] dependency into the relevant classes.
     *
     * @param apiService The API service used for interacting with the refresh token API.
     * @return A singleton instance of [RefreshTokenRepository].
     */

    @Provides
    @Singleton
    fun provideRefreshTokenRepository(
        apiService: RefreshTokenApi
    ): RefreshTokenRepository {
        return RefreshTokenRepositoryImp(apiService)
    }

    /**
     * Provides a singleton instance of the [RefreshTokenUseCase] implementation.
     *
     * This method is used by Dagger Hilt to inject the [RefreshTokenUseCase] dependency into the relevant classes.
     *
     * @param repository The repository used by the use case to interact with the API service.
     * @return A singleton instance of [RefreshTokenUseCase].
     */

    @Provides
    @Singleton
    fun provideRefreshTokenUseCase(
        repository: RefreshTokenRepository
    ): RefreshTokenUseCase {
        return RefreshTokenUseCasesImpl(repository)
    }

    /**
     * Provides a singleton instance of the [RefreshTokenApi] service.
     *
     * This method is used by Dagger Hilt to inject the [RefreshTokenApi] dependency into the relevant classes.
     *
     * @param retrofit The Retrofit instance used to create the API service.
     * @return A singleton instance of [RefreshTokenApi].
     */

    @Provides
    @Singleton
    fun provideRefreshTokenApiService(retrofit: Retrofit): RefreshTokenApi {
        return retrofit.create(RefreshTokenApi::class.java)
    }

}
