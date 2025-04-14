package com.example.eshaafihu_android_sdk.feature.auth.login.data.usecases

import com.example.eshaafihu_android_sdk.core.network.dataState.DataState
import com.example.eshaafihu_android_sdk.feature.auth.login.data.model.OtpRequestDto
import com.example.eshaafihu_android_sdk.feature.auth.login.domain.entity.OtpRequestResponse
import com.example.eshaafihu_android_sdk.feature.auth.login.domain.repository.LoginRepository
import com.example.eshaafihu_android_sdk.feature.auth.login.domain.usecases.LoginUseCases
import javax.inject.Inject


internal class LoginUseCasesImpl @Inject constructor(
    private val repository: LoginRepository
) : LoginUseCases {
    override suspend fun loginResponse(request: OtpRequestDto): DataState<OtpRequestResponse> {
        return repository.loginResponse(request)
    }
}