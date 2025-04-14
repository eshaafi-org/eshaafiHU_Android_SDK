package com.example.eshaafihu_android_sdk.feature.auth.login.domain.usecases

import com.example.eshaafihu_android_sdk.core.network.dataState.DataState
import com.example.eshaafihu_android_sdk.feature.auth.login.data.model.OtpRequestDto
import com.example.eshaafihu_android_sdk.feature.auth.login.domain.entity.OtpRequestResponse

interface LoginUseCases {
    suspend fun loginResponse(request: OtpRequestDto): DataState<OtpRequestResponse>
}