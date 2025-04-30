package com.example.eshaafihu_android_sdk.feature.refresh_token.data.usecases

import com.example.eshaafihu_android_sdk.core.network.dataState.DataState
import com.example.eshaafihu_android_sdk.feature.refresh_token.data.model.RefreshTokenPost
import com.example.eshaafihu_android_sdk.feature.refresh_token.domain.entity.RefreshTokenResponse
import com.example.eshaafihu_android_sdk.feature.refresh_token.domain.repository.RefreshTokenRepository
import com.example.eshaafihu_android_sdk.feature.refresh_token.domain.usecases.RefreshTokenUseCase
import javax.inject.Inject

/**
 * Implementation of the [RefreshTokenUseCase] that interacts with the [RefreshTokenRepository] to handle refreshing the authentication token.
 *
 * This class defines the use case for refreshing the authentication token by calling the appropriate method in the [RefreshTokenRepository].
 * It is responsible for the business logic related to refreshing tokens.
 *
 * @param repository The [RefreshTokenRepository] used for making the actual network request to refresh the token.
 */

internal class RefreshTokenUseCasesImpl @Inject constructor(
    private val repository: RefreshTokenRepository
) : RefreshTokenUseCase {

    /**
     * Refreshes the authentication token by calling the [refreshTokenResponse] method in the repository.
     *
     * This method sends a request to the repository to refresh the token and returns the result as a [DataState]. If successful,
     * it will contain the refreshed token in a [DataState.Success]. If there is an error, it will return a [DataState.Error] with the error message.
     *
     * @param request The request data for refreshing the token, including necessary details like device ID, refresh token, etc.
     * @return A [DataState] containing either a successful response with the refreshed token or an error message.
     */

    override suspend fun refreshTokenResponse(request: RefreshTokenPost): DataState<RefreshTokenResponse> {
        return repository.refreshTokenResponse(request)
    }
}