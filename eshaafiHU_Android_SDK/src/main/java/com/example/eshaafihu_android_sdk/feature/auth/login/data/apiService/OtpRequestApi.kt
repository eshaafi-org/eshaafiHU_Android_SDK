package com.example.eshaafihu_android_sdk.feature.auth.login.data.apiService

import com.example.eshaafihu_android_sdk.core.constants.Constants.Companion.PHONE_LOGIN
import com.example.eshaafihu_android_sdk.feature.auth.login.data.model.OtpRequestDto
import com.example.eshaafihu_android_sdk.feature.auth.login.data.model.OtpRequestResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

internal interface OtpRequestApi {
    @POST(PHONE_LOGIN)
    suspend fun loginWithPhone(@Body request: OtpRequestDto): Response<OtpRequestResponseDto>
}