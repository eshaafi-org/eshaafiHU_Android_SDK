package com.example.eshaafihu_android_sdk.core.network.networkModule

import com.example.eshaafihu_android_sdk.core.constants.Constants
import com.example.eshaafihu_android_sdk.core.network.networkInterceptors.HealthUnitInterceptor
import com.example.eshaafihu_android_sdk.core.network.networkInterceptors.NetworkInterceptor
import com.example.eshaafihu_android_sdk.core.network.networkInterceptors.PrettyLoggingInterceptor
import com.example.eshaafihu_android_sdk.core.network.networkInterceptors.TokenRefreshInterceptor
import com.example.eshaafihu_android_sdk.core.network.tokenManager.TokenManager
import com.example.eshaafihu_android_sdk.feature.refresh_token.domain.usecases.RefreshTokenUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Provider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    @Provides
    @Singleton
    fun provideTokenManager(refreshTokenUseCase: RefreshTokenUseCase): TokenManager {
        return TokenManager(refreshTokenUseCase)
    }

    @Provides
    @Singleton
    fun provideNetworkInterceptor(): NetworkInterceptor {
        return NetworkInterceptor()
    }

    @Provides
    @Singleton
    fun provideHealthUnitInterceptor(): HealthUnitInterceptor {
        return HealthUnitInterceptor()
    }

    @Provides
    @Singleton
    fun provideTokenRefreshInterceptor(
        tokenManagerProvider: Provider<TokenManager>
    ): TokenRefreshInterceptor {
        return TokenRefreshInterceptor(tokenManagerProvider)
    }

    @Provides
    @Singleton
    fun providePrettyLoggingInterceptor(): PrettyLoggingInterceptor {
        return PrettyLoggingInterceptor()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        networkInterceptor: NetworkInterceptor,
        healthUnitInterceptor: HealthUnitInterceptor,
        tokenRefreshInterceptor: TokenRefreshInterceptor,
        prettyLoggingInterceptor: PrettyLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(networkInterceptor)
            .addInterceptor(healthUnitInterceptor)
            .addInterceptor(tokenRefreshInterceptor)
            .addInterceptor(prettyLoggingInterceptor)
            .build()
    }
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.Companion.BASE_URL) // Replace with your API base URL
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }
}