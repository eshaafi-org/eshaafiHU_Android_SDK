package com.example.eshaafihu_android_sdk.core.network.tokenManager

import android.util.Log
import com.example.eshaafihu_android_sdk.core.app_logger.AppLogger
import com.example.eshaafihu_android_sdk.core.constants.Constants
import com.example.eshaafihu_android_sdk.core.network.dataState.DataState
import com.example.eshaafihu_android_sdk.feature.refresh_token.data.model.RefreshTokenPost
import com.example.eshaafihu_android_sdk.feature.refresh_token.domain.entity.RefreshTokenResponse
import com.example.eshaafihu_android_sdk.feature.refresh_token.domain.usecases.RefreshTokenUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * The `TokenManager` class is responsible for managing and refreshing authentication tokens.
 * It holds the current token and provides a method to refresh it using the `RefreshTokenUseCase`.
 * The class utilizes a `StateFlow` to notify subscribers of token updates.
 *
 * @constructor Injects a [RefreshTokenUseCase] to handle the token refresh logic.
 */
@Singleton
 class TokenManager @Inject constructor(
    private val refreshTokenUseCase: RefreshTokenUseCase
) {

    /**
     * The current token stored in the [TokenManager]. This token is updated when a successful
     * refresh occurs.
     */

    private var _token: RefreshTokenResponse? = null

    /**
     * A publicly accessible getter for the current token.
     *
     * @return The current [RefreshTokenResponse] token, or null if no token is available.
     */

    val token: RefreshTokenResponse?
        get() = _token

    private val _tokenFlow = MutableStateFlow<RefreshTokenResponse?>(null)
    val tokenFlow: StateFlow<RefreshTokenResponse?> get() = _tokenFlow

    fun updateToken(newToken: RefreshTokenResponse) {
        _token = newToken
        _tokenFlow.value = newToken
    }

    internal suspend fun refreshToken(): RefreshTokenResponse {
        val request = RefreshTokenPost(
               deviceId = "5322",
                type = 1,
                uuid = "7278d7ec-feb5-4f47-bbf1-a5d8f4d271c3",
                refreshToken = Constants.MY_TOKEN
        )

        return when (val result = refreshTokenUseCase.refreshTokenResponse(request)) {
            is DataState.Success -> {
                val newToken = result.data
                updateToken(newToken)
                newToken
            }

            is DataState.Error -> {
                throw Exception("Token refresh failed")
            }

            else -> throw Exception("Unknown error during token refresh")
        }
    }
}
