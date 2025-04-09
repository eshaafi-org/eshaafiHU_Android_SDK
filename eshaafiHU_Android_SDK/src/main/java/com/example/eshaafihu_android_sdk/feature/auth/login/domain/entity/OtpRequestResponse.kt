package com.example.eshaafihu_android_sdk.feature.auth.login.domain.entity

data class OtpRequestResponse(
    val responseCode: Int?,
    val message: String?,
    val response: OtpSendInnerResponse?
)

data class OtpSendInnerResponse(
    val message: String?,
    val pinSet: Boolean
)
