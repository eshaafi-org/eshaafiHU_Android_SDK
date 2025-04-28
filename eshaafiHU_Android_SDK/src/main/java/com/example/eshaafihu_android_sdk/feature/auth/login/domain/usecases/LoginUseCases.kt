package com.example.eshaafihu_android_sdk.feature.auth.login.domain.usecases

import com.example.eshaafihu_android_sdk.core.network.dataState.DataState
import com.example.eshaafihu_android_sdk.feature.auth.login.data.model.OtpRequestDto
import com.example.eshaafihu_android_sdk.feature.auth.login.domain.entity.OtpRequestResponse

/**
 * The `LoginUseCases` interface defines the contract for login-related use cases in the application.
 * It acts as an abstraction layer between the repository and the rest of the application, specifically
 * managing the business logic associated with the OTP login process.
 *
 * Implementing classes should define the business logic for the login functionality, utilizing the
 * corresponding repository to handle the network operations.
 */

interface LoginUseCases {

    /**
     * Initiates the OTP login process by using the provided request data and returns the login response.
     *
     * This method is responsible for invoking the appropriate repository method to perform the OTP
     * login. The response is returned as a [DataState] object, which can either be a [Success] containing
     * the login response or an [Error] indicating the failure reason.
     *
     * @param request The [OtpRequestDto] containing the phone number and OTP required for login.
     * @return A [DataState] object representing the result of the login attempt.
     *         On success, it contains an [OtpRequestResponse] object, and on failure, it contains an error message.
     */

    suspend fun loginResponse(request: OtpRequestDto): DataState<OtpRequestResponse>
}