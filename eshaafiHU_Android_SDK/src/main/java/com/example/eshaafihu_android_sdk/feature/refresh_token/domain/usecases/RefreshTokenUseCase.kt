package com.example.eshaafihu_android_sdk.feature.refresh_token.domain.usecases

import com.example.eshaafihu_android_sdk.core.network.dataState.DataState
import com.example.eshaafihu_android_sdk.feature.refresh_token.data.model.RefreshTokenPost
import com.example.eshaafihu_android_sdk.feature.refresh_token.domain.entity.RefreshTokenResponse

/**
 * Use case interface for refreshing the authentication token.
 *
 * This interface is responsible for orchestrating the logic related to refreshing the authentication token.
 * The implementing class should contain the business logic to interact with the corresponding repository
 * and manage any necessary transformations or error handling for refreshing the token.
 */

 interface RefreshTokenUseCase {
    /**
     * Refreshes the authentication token by invoking the appropriate repository method.
     *
     * This method acts as a bridge between the UI layer and the repository, calling the repository's method to refresh
     * the token and returning a [DataState] with the result.
     *
     * @param request The request data containing the necessary information like device ID, refresh token, etc.
     * @return A [DataState] containing either a [RefreshTokenResponse] on success or an error message on failure.
     */

    suspend fun refreshTokenResponse(request: RefreshTokenPost): DataState<RefreshTokenResponse>

}