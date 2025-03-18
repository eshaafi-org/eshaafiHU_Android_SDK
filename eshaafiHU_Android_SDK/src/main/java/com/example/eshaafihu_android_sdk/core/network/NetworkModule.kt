package com.example.eshaafihu_android_sdk.core.network

import android.util.Log
import com.example.eshaafihu_android_sdk.core.constants.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import retrofit2.HttpException
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    @Named("HealthUnitInterceptor")
    fun provideHealthUnitInterceptor(): Interceptor {
        return Interceptor { chain ->
            val originalRequest = chain.request()
            val requestBuilder = originalRequest.newBuilder()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")

            // Dynamic headers
            val config = NetworkConfigManager.currentConfig
            config?.let {
                requestBuilder.header("app-version", it.appVersion)
                requestBuilder.header("device-type", it.deviceType)
                requestBuilder.header("device-id", it.deviceId)
                if (!it.token.isNullOrEmpty()) {
                    requestBuilder.header("Authorization", "Bearer ${it.token}")
                }
            } ?: Log.w("NetworkModule", "RequestConfig is missing")

            chain.proceed(requestBuilder.build())
        }
    }

    @Provides
    @Singleton
    @Named("NetworkInterceptor")
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
    @Named("TokenRefreshInterceptor")
    fun provideTokenRefreshInterceptor(): Interceptor {
        return Interceptor { chain ->
            val response = chain.proceed(chain.request())
            if (response.code == 401) {
                val newRequest = chain.request().newBuilder()
                    .header("Authorization", "Bearer")
                    .build()
                return@Interceptor chain.proceed(newRequest)
            }
            response
        }
    }

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        @Named("HealthUnitInterceptor") healthUnitInterceptor: Interceptor,
        @Named("NetworkInterceptor") networkInterceptor: Interceptor,
        @Named("TokenRefreshInterceptor") tokenRefreshInterceptor: Interceptor,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(networkInterceptor)
            .addInterceptor(healthUnitInterceptor)
            .addInterceptor(tokenRefreshInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
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

    fun parseException(exception: Exception): String {
        return when (exception) {
            is HttpException -> {
                val errorBody = exception.response()?.errorBody()?.string()
                errorBody ?: "HTTP ${exception.code()} - ${exception.message()}"
            }
            is IOException -> "Network error. Please check your internet connection."
            else -> exception.localizedMessage ?: "Unknown error occurred"
        }
    }
}

object NetworkConfigManager {
    var currentConfig: RequestConfig? = null
}

class NoConnectivityException : IOException("No Internet Connection")
//@Singleton
//class TokenManager {
//    fun refreshToken(): String {
//        // Implement token refresh logic
//        return "new_token"
//    }
//}

sealed class DataState<out T> {
    data class Success<T>(val data: T) : DataState<T>()
    data class Error(val exception: String) : DataState<Nothing>()
    object Loading : DataState<Nothing>()
}