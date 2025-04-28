package com.example.eshaafihu_android_sdk.feature.auth.login.data.apiService

import com.example.eshaafihu_android_sdk.core.constants.Constants.Companion.PHONE_LOGIN
import com.example.eshaafihu_android_sdk.feature.auth.login.data.model.OtpRequestDto
import com.example.eshaafihu_android_sdk.feature.auth.login.data.model.OtpRequestResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

internal interface OtpRequestApi {
    /**
     * Sends an OTP request for phone-based login.
     *
     * This method makes a `POST` request to the [PHONE_LOGIN] endpoint with the provided [OtpRequestDto]
     * as the request body. The method returns a response containing the [OtpRequestResponseDto].
     *
     * @param request The [OtpRequestDto] containing the phone number and other login details required for OTP-based login.
     * @return A [Response] containing the [OtpRequestResponseDto] that contains the response details,
     *         such as the success status and any additional information from the server.
     */

    @POST(PHONE_LOGIN)
    suspend fun loginWithPhone(@Body request: OtpRequestDto): Response<OtpRequestResponseDto>
}