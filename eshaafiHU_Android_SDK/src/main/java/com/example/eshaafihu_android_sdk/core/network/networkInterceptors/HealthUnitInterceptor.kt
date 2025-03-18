package com.example.eshaafihu_android_sdk.core.network.networkInterceptors

import com.example.eshaafihu_android_sdk.core.network.networkConfiguration.NetworkConfigManager
import okhttp3.Interceptor
import okhttp3.Response

class HealthUnitInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val requestBuilder = originalRequest.newBuilder()
            .header("Accept", "application/json")
            .header("Content-Type", "application/json")

        // Dynamic headers
        val config = NetworkConfigManager.getConfig()
        config?.let {
            requestBuilder.header("app-version", it.appVersion)
            requestBuilder.header("device-type", it.deviceType)
            requestBuilder.header("device-id", it.deviceId)
            if (it.token.isNotEmpty()) {
                requestBuilder.header("Authorization", "Bearer ${it.token}")
            }
        }

        return chain.proceed(requestBuilder.build())
    }
}