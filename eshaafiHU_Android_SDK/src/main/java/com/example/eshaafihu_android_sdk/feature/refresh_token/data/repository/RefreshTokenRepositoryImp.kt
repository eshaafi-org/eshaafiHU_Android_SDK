package com.example.eshaafihu_android_sdk.feature.refresh_token.data.repository

import com.example.eshaafihu_android_sdk.core.network.dataState.DataState
import com.example.eshaafihu_android_sdk.core.network.networkUtils.NetworkUtils
import com.example.eshaafihu_android_sdk.feature.refresh_token.data.apiService.RefreshTokenApi
import com.example.eshaafihu_android_sdk.feature.refresh_token.data.model.RefreshTokenPost
import com.example.eshaafihu_android_sdk.feature.refresh_token.domain.entity.RefreshTokenResponse
import com.example.eshaafihu_android_sdk.feature.refresh_token.domain.repository.RefreshTokenRepository
import javax.inject.Inject


internal class RefreshTokenRepositoryImp @Inject constructor(
    private val apiService: RefreshTokenApi
) : RefreshTokenRepository {
    override suspend fun refreshTokenResponse(request: RefreshTokenPost): DataState<RefreshTokenResponse> {
        return try {
            val response = apiService.refreshTokenRequest(request)
            if (response.isSuccessful && response.body() != null) {
                val domainModel = response.body()!!.toDomain()  // ✅ Convert DTO to Domain Model
                DataState.Success(domainModel)
            } else {
                DataState.Error(response.message() ?: "Unknown error")
            }
        } catch (e: Exception) {
            DataState.Error(NetworkUtils.parseException(e))
        }
    }
}