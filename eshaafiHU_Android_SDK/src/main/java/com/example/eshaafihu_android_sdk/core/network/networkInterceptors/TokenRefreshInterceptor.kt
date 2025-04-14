package com.example.eshaafihu_android_sdk.core.network.networkInterceptors

import com.example.eshaafihu_android_sdk.core.network.tokenManager.TokenManager
import okhttp3.Interceptor
import okhttp3.Response

internal class TokenRefreshInterceptor(private val tokenManager: TokenManager) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val response = chain.proceed(originalRequest)

        if (response.code == 401) { // Unauthorized - refresh token
            synchronized(this) {
                val newToken = tokenManager.refreshToken()
                val newRequest = originalRequest.newBuilder()
                    .header("Authorization", "Bearer $newToken")
                    .build()
                return chain.proceed(newRequest)
            }
        }
        return response
    }
}