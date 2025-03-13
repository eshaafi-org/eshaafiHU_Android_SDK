package com.example.eshaafi_healthunit.app.data.di

import android.util.Log
import com.example.eshaafi_healthunit.app.data.home.api.HomeApi
import com.example.eshaafi_healthunit.app.utils.Constant.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val originalRequest = chain.request()
                val requestBuilder = originalRequest.newBuilder()

                // Add required headers
                requestBuilder.header("Accept", "application/json")
                requestBuilder.header("Content-Type", "application/json")
                requestBuilder.header("app-version", "1.9")
                requestBuilder.header("device-type", "Android")

                // Get device ID dynamically
//                val deviceId = getDeviceId(MyApplication.get()!!)
                requestBuilder.header("device-id", "121212121")

                // Retrieve the token from SharedPreferences
//                val token1= SharedPreferences.getIdToken()
                val token = ""
                if (!token.isNullOrEmpty()) {
                    requestBuilder.header("Authorization", "Bearer $token")
                } else {
                    // Log warning if token is missing
                    Log.w("NetworkModule", "Authorization token is missing")
                }

                // Log headers for debugging
                val newRequest = requestBuilder.build()
                Log.d("API Request", "URL: ${newRequest.url}")
                Log.d("API Request", "Headers: ${newRequest.headers}")

                chain.proceed(newRequest)
            }
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL) // Set your base URL
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideHomeApi(retrofit: Retrofit): HomeApi {
        return retrofit.create(HomeApi::class.java)
    }
}



