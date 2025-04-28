package com.example.eshaafihu_android_sdk.feature.auth.login.data.usecases

import com.example.eshaafihu_android_sdk.core.network.dataState.DataState
import com.example.eshaafihu_android_sdk.feature.auth.login.data.model.OtpRequestDto
import com.example.eshaafihu_android_sdk.feature.auth.login.domain.entity.OtpRequestResponse
import com.example.eshaafihu_android_sdk.feature.auth.login.domain.repository.LoginRepository
import com.example.eshaafihu_android_sdk.feature.auth.login.domain.usecases.LoginUseCases
import javax.inject.Inject

/**
 * The `LoginUseCasesImpl` class is an implementation of the [LoginUseCases] interface.
 * This class serves as the entry point for handling the login use cases, leveraging the
 * [LoginRepository] to perform the actual network operations related to OTP-based login.
 *
 * @param repository The [LoginRepository] used to interact with the remote API for login operations.
 */

internal class LoginUseCasesImpl @Inject constructor(
    private val repository: LoginRepository
) : LoginUseCases {

    /**
     * Initiates the OTP login process by delegating the task to the repository's login response function.
     *
     * This function accepts an [OtpRequestDto] as input and calls the repository to perform the actual
     * OTP login operation. The result is then returned as a [DataState] indicating either success or failure.
     *
     * @param request The [OtpRequestDto] containing the phone number and OTP information required for login.
     * @return A [DataState] containing the result of the login attempt.
     *         On success, it contains the mapped [OtpRequestResponse] domain model.
     *         On failure, it contains an error message indicating the issue.
     */

    override suspend fun loginResponse(request: OtpRequestDto): DataState<OtpRequestResponse> {
        return repository.loginResponse(request)
    }
}