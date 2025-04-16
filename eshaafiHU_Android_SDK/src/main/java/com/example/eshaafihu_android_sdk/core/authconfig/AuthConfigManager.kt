package com.example.eshaafihu_android_sdk.core.authconfig

object AuthConfigManager {
    private var config: MyAuthConfig? = null

    fun updateConfig(newConfig: MyAuthConfig) {
        config = newConfig
    }

    fun getConfig(): MyAuthConfig? {
        return config
    }
}