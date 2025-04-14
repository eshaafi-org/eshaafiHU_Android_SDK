package com.example.eshaafihu_android_sdk.feature.refresh_token.domain.entity

data class RefreshTokenResponse(
    val statusCode: Int?,
    val message: String?,
    val refreshToken: RefreshToken
)

data class RefreshToken(
    val idToken: String,
    val accessToken: String
)
