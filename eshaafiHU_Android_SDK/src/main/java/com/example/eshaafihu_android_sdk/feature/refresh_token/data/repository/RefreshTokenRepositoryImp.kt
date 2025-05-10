package com.example.eshaafihu_android_sdk.feature.refresh_token.data.repository

import com.example.eshaafihu_android_sdk.core.app_logger.AppLogger
import com.example.eshaafihu_android_sdk.core.network.dataState.DataState
import com.example.eshaafihu_android_sdk.core.network.networkUtils.NetworkUtils
import com.example.eshaafihu_android_sdk.feature.refresh_token.data.apiService.RefreshTokenApi
import com.example.eshaafihu_android_sdk.feature.refresh_token.data.model.RefreshTokenPost
import com.example.eshaafihu_android_sdk.feature.refresh_token.domain.entity.RefreshTokenResponse
import com.example.eshaafihu_android_sdk.feature.refresh_token.domain.repository.RefreshTokenRepository
import javax.inject.Inject

/**
 * Implementation of the [RefreshTokenRepository] that interacts with the [RefreshTokenApi] to refresh the authentication token.
 *
 * This repository is responsible for making network requests to the backend to refresh the token and mapping the response
 * into the appropriate domain model.
 *
 * @param apiService The [RefreshTokenApi] service used for making the network request to refresh the token.
 */

internal class RefreshTokenRepositoryImp @Inject constructor(
    private val apiService: RefreshTokenApi
) : RefreshTokenRepository {

    /**
     * Makes a request to refresh the authentication token.
     *
     * This method sends a request to the backend API to refresh the token and handles the response. If the request is successful,
     * it converts the response body into a domain model and returns a [DataState.Success] containing the domain model.
     * If the request fails, it returns a [DataState.Error] containing the error message.
     *
     * @param request The request data for refreshing the token, including necessary details like device ID, refresh token, etc.
     * @return A [DataState] containing either a successful response with the domain model or an error message.
     */

    override suspend fun refreshTokenResponse(request: RefreshTokenPost): DataState<RefreshTokenResponse> {
        return try {
            val response = apiService.refreshTokenRequest(request)
            AppLogger.d(message = "response=$response")
            if (response.isSuccessful && response.body() != null) {
                // Convert the response body (DTO) into a domain model and return success
                val domainModel = response.body()!!.toDomain()  // ✅ Convert DTO to Domain Model
                AppLogger.d(message = "API Success: $domainModel")
                DataState.Success(domainModel)
            } else {
                AppLogger.e(message = "API Error: ${response.message()}")
                DataState.Error(response.message() ?: "Unknown error")
            }
        } catch (e: Exception) {
            AppLogger.e(message = "API Exception: ${NetworkUtils.parseException(e)}")
            DataState.Error(NetworkUtils.parseException(e))

        }
    }
}