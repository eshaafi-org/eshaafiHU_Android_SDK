package com.example.eshaafihu_android_sdk.feature.auth.login.data.model

import com.google.gson.annotations.SerializedName

data class OtpRequestDto(
    @SerializedName("phone_no") val phoneNo: String,
    @SerializedName("type") val type: Int
)

