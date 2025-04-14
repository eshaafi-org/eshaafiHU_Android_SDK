package com.example.eshaafihu_android_sdk.feature.auth.login.data.model

import com.example.eshaafihu_android_sdk.feature.auth.login.domain.entity.OtpRequestResponse
import com.example.eshaafihu_android_sdk.feature.auth.login.domain.entity.OtpSendInnerResponse
import com.google.gson.annotations.SerializedName

internal data class OtpRequestResponseDto(
    @SerializedName("responseCode") val responseCode: Int?,
    @SerializedName("message") val message: String?,
    @SerializedName("response") val response: OtpSendInnerResponseDto?
) {
    fun toDomain(): OtpRequestResponse {
        return OtpRequestResponse(
            responseCode = responseCode,
            message = message,
            response = response?.toDomain()
        )
    }
}

internal data class OtpSendInnerResponseDto(
    @SerializedName("message") val message: String?,
    @SerializedName("pinSet") val pinSet: Boolean
) {
    fun toDomain(): OtpSendInnerResponse {
        return OtpSendInnerResponse(
            message = message,
            pinSet = pinSet
        )
    }
}


