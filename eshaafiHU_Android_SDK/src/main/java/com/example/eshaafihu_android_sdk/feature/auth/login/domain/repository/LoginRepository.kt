package com.example.eshaafihu_android_sdk.feature.auth.login.domain.repository

import com.example.eshaafihu_android_sdk.core.network.dataState.DataState
import com.example.eshaafihu_android_sdk.feature.auth.login.data.model.OtpRequestDto
import com.example.eshaafihu_android_sdk.feature.auth.login.domain.entity.OtpRequestResponse

/**
 * The `LoginRepository` interface defines the contract for the login functionality in the application.
 * It outlines the methods that are used to perform login-related network operations, such as initiating
 * an OTP-based login request.
 *
 * Implementing classes should define the actual behavior for handling the login response, typically by
 * interacting with a remote API or local data sources.
 */

interface LoginRepository {
    /**
     * Initiates the OTP login process by sending the necessary data to the remote API.
     *
     * This method takes an [OtpRequestDto] containing the user's phone number and OTP, and performs
     * the necessary network operations to authenticate the user. The response is returned as a
     * [DataState] which can either be a [Success] containing the login response or an [Error] indicating
     * the failure reason.
     *
     * @param request The [OtpRequestDto] containing the phone number and OTP data required for the login.
     * @return A [DataState] object representing the result of the login attempt.
     *         It will contain a [OtpRequestResponse] on success, or an error message on failure.
     */

    suspend fun loginResponse(request: OtpRequestDto): DataState<OtpRequestResponse>
}