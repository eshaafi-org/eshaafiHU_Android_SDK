package com.example.eshaafihu_android_sdk.feature.refresh_token.data.apiService

import com.example.eshaafihu_android_sdk.core.constants.Constants.Companion.REFRESH_TOKEN
import com.example.eshaafihu_android_sdk.feature.refresh_token.data.model.RefreshTokenPost
import com.example.eshaafihu_android_sdk.feature.refresh_token.data.model.RefreshTokenResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


internal interface RefreshTokenApi {
    @POST(REFRESH_TOKEN)
    suspend fun refreshTokenRequest(@Body request: RefreshTokenPost): Response<RefreshTokenResponseDto>
}