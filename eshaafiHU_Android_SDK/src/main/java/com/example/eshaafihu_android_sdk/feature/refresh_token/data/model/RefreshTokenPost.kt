package com.example.eshaafihu_android_sdk.feature.refresh_token.data.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class RefreshTokenPost(
    @SerializedName("refresh_token")
    val refreshToken: String = "",
    @SerializedName("type")
    val type: Int = 0,
    @SerializedName("uuid")
    val uuid: String = "",
    @SerializedName("deviceId")
    val deviceId: String = ""
)