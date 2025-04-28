package com.example.eshaafihu_android_sdk.feature.refresh_token.domain.repository

import com.example.eshaafihu_android_sdk.core.network.dataState.DataState
import com.example.eshaafihu_android_sdk.feature.refresh_token.data.model.RefreshTokenPost
import com.example.eshaafihu_android_sdk.feature.refresh_token.domain.entity.RefreshTokenResponse


/**
 * Repository interface responsible for handling the data operations related to refreshing the authentication token.
 *
 * This interface defines the contract for refreshing the token. Implementing classes should provide the actual logic for
 * making network requests to refresh the authentication token and handling the response accordingly.
 */

interface RefreshTokenRepository {
    /**
     * Refreshes the authentication token by sending a request to the appropriate API.
     *
     * This method should be implemented to handle the logic for refreshing the token, typically by sending a network request
     * to a refresh token endpoint. It returns a [DataState] that contains either the refreshed token on success or an error message.
     *
     * @param request The request data containing necessary information like device ID, refresh token, etc.
     * @return A [DataState] containing either a [RefreshTokenResponse] on success or an error message on failure.
     */

    suspend fun refreshTokenResponse(request: RefreshTokenPost): DataState<RefreshTokenResponse>
}