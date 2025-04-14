package com.example.eshaafihu_android_sdk.feature.refresh_token.data.model

import androidx.annotation.Keep
import com.example.eshaafihu_android_sdk.feature.refresh_token.domain.entity.RefreshToken
import com.example.eshaafihu_android_sdk.feature.refresh_token.domain.entity.RefreshTokenResponse
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

// data/model/RefreshTokenResponseDto.kt
@Keep
data class RefreshTokenResponseDto(
    @SerializedName("responseCode")
    val statusCode: Int? = null,

    @SerializedName("message")
    val message: String? = null,

    @SerializedName("response")
    val response: RefreshTokenPayload = RefreshTokenPayload()
) {
    @Keep
    data class RefreshTokenPayload(
        @SerializedName("IdToken")
        val idToken: String = "",

        @SerializedName("AccessToken")
        @Expose
        val accessToken: String = ""
    )

    fun toDomain(): RefreshTokenResponse {
        return RefreshTokenResponse(
            statusCode = this.statusCode,
            message = this.message,
            refreshToken = RefreshToken(
                idToken = response.idToken,
                accessToken = response.accessToken
            )
        )
    }
}


