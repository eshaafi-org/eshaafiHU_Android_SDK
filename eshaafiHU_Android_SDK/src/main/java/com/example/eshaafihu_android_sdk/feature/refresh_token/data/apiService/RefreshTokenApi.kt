package com.example.eshaafihu_android_sdk.feature.refresh_token.data.apiService

import com.example.eshaafihu_android_sdk.core.constants.Constants.Companion.REFRESH_TOKEN
import com.example.eshaafihu_android_sdk.feature.refresh_token.data.model.RefreshTokenPost
import com.example.eshaafihu_android_sdk.feature.refresh_token.data.model.RefreshTokenResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


/**
 * Interface representing the API service for refreshing tokens.
 *
 * This interface defines the endpoint for refreshing the authentication token. The API is called using a POST request
 * to the defined endpoint, and the request body is a [RefreshTokenPost] object containing the necessary data
 * for the refresh token request. The response is expected to be a [RefreshTokenResponseDto].
 */


internal interface RefreshTokenApi {

    /**
     * Requests a new refresh token using the provided [RefreshTokenPost] object.
     *
     * This method sends a POST request to the server to refresh the authentication token.
     * If successful, the response will contain a [RefreshTokenResponseDto] object with the new token.
     *
     * @param request The request body containing the necessary data to refresh the token.
     * @return A [Response] containing the [RefreshTokenResponseDto].
     */

    @POST(REFRESH_TOKEN)
    suspend fun refreshTokenRequest(@Body request: RefreshTokenPost): Response<RefreshTokenResponseDto>
}