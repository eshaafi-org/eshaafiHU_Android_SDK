package com.example.eshaafihu_android_sdk.feature.refresh_token.domain.repository

import com.example.eshaafihu_android_sdk.core.network.dataState.DataState
import com.example.eshaafihu_android_sdk.feature.refresh_token.data.model.RefreshTokenPost
import com.example.eshaafihu_android_sdk.feature.refresh_token.domain.entity.RefreshTokenResponse


interface RefreshTokenRepository {
    suspend fun refreshTokenResponse(request: RefreshTokenPost): DataState<RefreshTokenResponse>
}