package com.example.eshaafihu_android_sdk.feature.refresh_token.data.usecases

import com.example.eshaafihu_android_sdk.core.network.dataState.DataState
import com.example.eshaafihu_android_sdk.feature.refresh_token.data.model.RefreshTokenPost
import com.example.eshaafihu_android_sdk.feature.refresh_token.domain.entity.RefreshTokenResponse
import com.example.eshaafihu_android_sdk.feature.refresh_token.domain.repository.RefreshTokenRepository
import com.example.eshaafihu_android_sdk.feature.refresh_token.domain.usecases.RefreshTokenUseCase
import javax.inject.Inject


internal class RefreshTokenUseCasesImpl @Inject constructor(
    private val repository: RefreshTokenRepository
) : RefreshTokenUseCase {
    override suspend fun refreshTokenResponse(request: RefreshTokenPost): DataState<RefreshTokenResponse> {
        return repository.refreshTokenResponse(request)
    }
}