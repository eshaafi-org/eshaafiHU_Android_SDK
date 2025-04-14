package com.example.eshaafihu_android_sdk.feature.auth.login.data.repository

import com.example.eshaafihu_android_sdk.core.network.dataState.DataState
import com.example.eshaafihu_android_sdk.core.network.networkUtils.NetworkUtils
import com.example.eshaafihu_android_sdk.feature.auth.login.data.apiService.OtpRequestApi
import com.example.eshaafihu_android_sdk.feature.auth.login.data.model.OtpRequestDto
import com.example.eshaafihu_android_sdk.feature.auth.login.domain.entity.OtpRequestResponse
import com.example.eshaafihu_android_sdk.feature.auth.login.domain.repository.LoginRepository

import javax.inject.Inject


internal class LoginRepositoryImp @Inject constructor(
    private val apiService: OtpRequestApi
) : LoginRepository {

    override suspend fun loginResponse(request: OtpRequestDto): DataState<OtpRequestResponse> {
        return try {
            val response = apiService.loginWithPhone(request)
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