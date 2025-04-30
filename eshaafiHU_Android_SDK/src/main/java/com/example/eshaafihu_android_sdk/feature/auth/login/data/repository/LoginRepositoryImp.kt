package com.example.eshaafihu_android_sdk.feature.auth.login.data.repository

import com.example.eshaafihu_android_sdk.core.network.dataState.DataState
import com.example.eshaafihu_android_sdk.core.network.networkUtils.NetworkUtils
import com.example.eshaafihu_android_sdk.feature.auth.login.data.apiService.OtpRequestApi
import com.example.eshaafihu_android_sdk.feature.auth.login.data.model.OtpRequestDto
import com.example.eshaafihu_android_sdk.feature.auth.login.domain.entity.OtpRequestResponse
import com.example.eshaafihu_android_sdk.feature.auth.login.domain.repository.LoginRepository

import javax.inject.Inject



/**
 * The `LoginRepositoryImp` class is an implementation of the [LoginRepository] interface,
 * responsible for handling login-related network operations using the OTP (One-Time Password)
 * authentication mechanism.
 *
 * This class interacts with the [OtpRequestApi] to perform the login operation and returns
 * the result in the form of a [DataState] which indicates either success or failure.
 *
 * @param apiService The [OtpRequestApi] used to perform OTP login network requests.
 */

internal class LoginRepositoryImp @Inject constructor(
    private val apiService: OtpRequestApi
) : LoginRepository {

    /**
     * Performs the OTP login operation by calling the [OtpRequestApi.loginWithPhone] API.
     *
     * This function makes a network request to authenticate the user via OTP. If the request is
     * successful and a valid response body is returned, it maps the response to a domain model.
     * In case of failure, it returns an error message based on the response or exception encountered.
     *
     * @param request The [OtpRequestDto] containing the phone number and OTP information for login.
     * @return A [DataState] containing the result of the login operation.
     *         On success, it contains the mapped [OtpRequestResponse] domain model.
     *         On failure, it contains an error message.
     */

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