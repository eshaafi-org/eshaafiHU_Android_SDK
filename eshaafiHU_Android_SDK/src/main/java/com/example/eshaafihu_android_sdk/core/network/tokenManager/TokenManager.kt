package com.example.eshaafihu_android_sdk.core.network.tokenManager

import android.util.Log
import com.example.eshaafihu_android_sdk.core.constants.Constants
import com.example.eshaafihu_android_sdk.core.network.dataState.DataState
import com.example.eshaafihu_android_sdk.feature.refresh_token.data.model.RefreshTokenPost
import com.example.eshaafihu_android_sdk.feature.refresh_token.domain.entity.RefreshTokenResponse
import com.example.eshaafihu_android_sdk.feature.refresh_token.domain.usecases.RefreshTokenUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
 class TokenManager @Inject constructor(
    private val refreshTokenUseCase: RefreshTokenUseCase
) {

    private var _token: RefreshTokenResponse? = null
    val token: RefreshTokenResponse?
        get() = _token

    private val _tokenFlow = MutableStateFlow<RefreshTokenResponse?>(null)
    val tokenFlow: StateFlow<RefreshTokenResponse?> get() = _tokenFlow

    fun updateToken(newToken: RefreshTokenResponse) {
        _token = newToken
        _tokenFlow.value = newToken
    }

    internal suspend fun refreshToken(): RefreshTokenResponse {
        Log.d("TokenManager", "Refreshing token...")

        val request = RefreshTokenPost(
               deviceId = "5322",
                type = 1,
                uuid = "7278d7ec-feb5-4f47-bbf1-a5d8f4d271c3",
                refreshToken = Constants.MY_TOKEN
        )

        return when (val result = refreshTokenUseCase.refreshTokenResponse(request)) {
            is DataState.Success -> {
                val newToken = result.data
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
