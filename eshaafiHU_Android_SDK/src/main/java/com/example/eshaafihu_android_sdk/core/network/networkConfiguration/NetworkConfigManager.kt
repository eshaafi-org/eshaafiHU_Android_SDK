package com.example.eshaafihu_android_sdk.core.network.networkConfiguration

object NetworkConfigManager {
    private var config: RequestConfig? = null

    fun updateConfig(newConfig: RequestConfig) {
        config = newConfig
    }

    fun getConfig(): RequestConfig? {
        return config
    }
}