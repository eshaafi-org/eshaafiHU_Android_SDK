package com.example.eshaafihu_android_sdk.core.network

import android.util.Log
import com.example.eshaafihu_android_sdk.core.constants.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import javax.inject.Named

@Provides
@Singleton
@Named("BaseUrl")
fun provideBaseUrl(): String = Constants.BASE_URL

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHeaderInterceptor(): Interceptor {
        return Interceptor { chain ->
            val originalRequest = chain.request()
            val requestBuilder = originalRequest.newBuilder()
            requestBuilder.header("Accept", "application/json")
            requestBuilder.header("Content-Type", "application/json")

            // Dynamic headers
            val config = NetworkConfigManager.currentConfig
            if (config != null) {
                requestBuilder.header("app-version", config.appVersion)
                requestBuilder.header("device-type", config.deviceType)
                requestBuilder.header("device-id", config.deviceId)
                config.token.let { token ->
                    requestBuilder.header("Authorization", "Bearer $token")
                }
            } else {
                Log.w("NetworkModule", "RequestConfig is missing")
            }

            // Token handling
            val token = config?.token
            if (!token.isNullOrEmpty()) {
                requestBuilder.header("Authorization", "Bearer $token")
            } else {
                Log.w("NetworkModule", "Authorization token is missing")
            }
            chain.proceed(requestBuilder.build())
        }
    }

    @Provides
    @Singleton
    fun provideNetworkInterceptor(): Interceptor {
        return Interceptor { chain ->
            if (!NetworkUtils.isNetworkAvailable()) {
                throw NoConnectivityException()
            }
            chain.proceed(chain.request())
        }
    }

    @Provides
    @Singleton
    fun provideTokenRefreshInterceptor(tokenManager: TokenManager): Interceptor {
        return Interceptor { chain ->
            val response = chain.proceed(chain.request())
            if (response.code == 401) {
                val newToken = tokenManager.refreshToken()
                val newRequest = chain.request().newBuilder()
                    .header("Authorization", "Bearer $newToken")
                    .build()
                return@Interceptor chain.proceed(newRequest)
            }
            response
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        headerInterceptor: Interceptor,
        networkInterceptor: Interceptor,
        tokenRefreshInterceptor: Interceptor,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(networkInterceptor)
            .addInterceptor(headerInterceptor)
            .addInterceptor(tokenRefreshInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, @Named("BaseUrl") baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }
}

object RequestConfig {
    var appVersion: String = ""
    var deviceType: String = ""
    var deviceId: String = ""
    var token: String = ""
}

object NetworkUtils {
    fun isNetworkAvailable(): Boolean {
        // Implement actual network check
        return true
    }
}

object NetworkConfigManager {
    var currentConfig: RequestConfig? = null
}

class NoConnectivityException : IOException("No Internet Connection")

class TokenManager {
    fun refreshToken(): String {
        // Implement token refresh logic
        return "new_token"
    }
}