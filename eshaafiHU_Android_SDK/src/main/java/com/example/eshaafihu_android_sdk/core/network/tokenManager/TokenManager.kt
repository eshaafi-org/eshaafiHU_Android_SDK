package com.example.eshaafihu_android_sdk.core.network.tokenManager

import android.util.Log
import com.example.eshaafihu_android_sdk.core.constants.Constants
import com.example.eshaafihu_android_sdk.core.network.dataState.DataState
import com.example.eshaafihu_android_sdk.feature.refresh_token.data.model.RefreshTokenPost
import com.example.eshaafihu_android_sdk.feature.refresh_token.domain.usecases.RefreshTokenUseCase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class TokenManager @Inject constructor(
    private val refreshTokenUseCase: RefreshTokenUseCase
) {

    private var _token: String? = null
    val token: String?
        get() = _token

    fun updateToken(newToken: String) {
        _token = newToken
    }

    suspend fun refreshToken(): String {
        Log.d("TokenManager", "Refreshing token...")

        val request = RefreshTokenPost(
               deviceId = "5322",
                type = 1,
                uuid = "7278d7ec-feb5-4f47-bbf1-a5d8f4d271c3",
                refreshToken = Constants.MY_TOKEN
        )

        return when (val result = refreshTokenUseCase.refreshTokenResponse(request)) {
            is DataState.Success -> {
                val newToken = result.data.refreshToken.accessToken
                Log.e("tokenUpdate","${result.data}")
                updateToken(newToken)
                newToken
            }

            is DataState.Error -> {
                Log.e("tokenUpdate", "Failed to refresh token: ${result.exception}")
                throw Exception("Token refresh failed")
            }

            else -> throw Exception("Unknown error during token refresh")
        }
    }
}
