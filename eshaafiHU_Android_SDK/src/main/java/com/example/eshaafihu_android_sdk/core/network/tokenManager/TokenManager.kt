package com.example.eshaafihu_android_sdk.core.network.tokenManager

import android.util.Log
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TokenManager @Inject constructor() {

    var token: String? = null
        private set

    fun updateToken(newToken: String) {
        token = newToken
    }

    fun refreshToken(): String {
        // Implement actual token refresh logic (API call)
        Log.d("TokenManager", "Refreshing token...")
        val newToken = "new_generated_token" // Replace with actual refreshed token
        updateToken(newToken)
        return newToken
    }
}