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

/**
 * A Dagger module that provides the necessary network-related components for the application.
 * It includes the configuration of network interceptors, OkHttpClient, and Retrofit client.
 * This module is installed in the SingletonComponent, meaning the provided instances are singletons
 * and live throughout the application's lifecycle.
 *
 * @module NetworkModule
 */

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    /**
     * Provides an instance of [TokenManager] that handles token management and token refreshing.
     * This is a singleton instance, used to manage and refresh the authentication token.
     *
     * @param refreshTokenUseCase A use case responsible for handling token refreshing logic.
     * @return A singleton instance of [TokenManager].
     */

    @Provides
    @Singleton
    fun provideTokenManager(refreshTokenUseCase: RefreshTokenUseCase): TokenManager {
        return TokenManager(refreshTokenUseCase)
    }

    /**
     * Provides an instance of [NetworkInterceptor] used to check the network availability.
     * This interceptor will throw a [NoConnectivityException] if there is no network connection.
     *
     * @return A singleton instance of [NetworkInterceptor].
     */

    @Provides
    @Singleton
    fun provideNetworkInterceptor(): NetworkInterceptor {
        return NetworkInterceptor()
    }

    /**
     * Provides an instance of [HealthUnitInterceptor] used for adding headers such as device type
     * and version to the request for specific network calls related to health unit operations.
     *
     * @return A singleton instance of [HealthUnitInterceptor].
     */

    @Provides
    @Singleton
    fun provideHealthUnitInterceptor(): HealthUnitInterceptor {
        return HealthUnitInterceptor()
    }

    /**
     * Provides an instance of [TokenRefreshInterceptor] that refreshes the authentication token
     * when a 401 Unauthorized response is received. It uses the provided [TokenManager] to get a new token.
     *
     * @param tokenManagerProvider A provider for the [TokenManager] to retrieve the token refresh logic.
     * @return A singleton instance of [TokenRefreshInterceptor].
     */

    @Provides
    @Singleton
    fun provideTokenRefreshInterceptor(
        tokenManagerProvider: Provider<TokenManager>
    ): TokenRefreshInterceptor {
        return TokenRefreshInterceptor(tokenManagerProvider)
    }

    /**
     * Provides an instance of [PrettyLoggingInterceptor] that logs network requests and responses
     * in a human-readable format, including JSON formatting.
     *
     * @return A singleton instance of [PrettyLoggingInterceptor].
     */

    @Provides
    @Singleton
    fun providePrettyLoggingInterceptor(): PrettyLoggingInterceptor {
        return PrettyLoggingInterceptor()
    }

    /**
     * Provides an instance of [OkHttpClient] configured with multiple interceptors for network connectivity
     * checks, header injections, token refresh, and logging.
     *
     * @param networkInterceptor An interceptor for checking network connectivity.
     * @param healthUnitInterceptor An interceptor for adding health unit specific headers.
     * @param tokenRefreshInterceptor An interceptor that refreshes the token on a 401 Unauthorized response.
     * @param prettyLoggingInterceptor An interceptor that logs requests and responses.
     * @return A singleton instance of [OkHttpClient].
     */

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

    /**
     * Provides an instance of [Retrofit] configured with the base URL and Gson converter for JSON parsing.
     * This Retrofit instance is used for making API calls.
     *
     * @param okHttpClient The OkHttpClient instance used for network communication.
     * @return A singleton instance of [Retrofit].
     */

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