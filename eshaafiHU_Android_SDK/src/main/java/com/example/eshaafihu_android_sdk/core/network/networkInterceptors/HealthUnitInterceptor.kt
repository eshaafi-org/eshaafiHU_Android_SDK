package com.example.eshaafihu_android_sdk.core.network.networkInterceptors

import com.example.eshaafihu_android_sdk.core.authconfig.AuthConfigManager
import com.example.eshaafihu_android_sdk.core.network.networkConfiguration.NetworkConfigManager
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Intercepts HTTP requests to add necessary headers for authentication, app version, device type, and device ID.
 * This class is responsible for modifying the original HTTP request by adding required headers before it is sent.
 *
 * @property chain The interceptor chain that allows us to modify the request before proceeding with the network call.
 */

internal class HealthUnitInterceptor : Interceptor {

    /**
     * Intercepts the HTTP request, adding necessary headers such as authorization token, app version, device type,
     * and device ID to the original request.
     *
     * @param chain The interceptor chain that provides access to the original request.
     * @return [Response] The response from the network after processing the modified request.
     */

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val requestBuilder = originalRequest.newBuilder()
            .header("Accept", "application/json")
            .header("Content-Type", "application/json")

        // Dynamic headers
        val config = NetworkConfigManager.getConfig()
        config?.let {
            requestBuilder.header("app-version", it.appVersion)
            requestBuilder.header("device-type", it.deviceType)
            requestBuilder.header("device-id", it.deviceId)
        }
        val authConfig = AuthConfigManager.getConfig()
        authConfig?.let {
            requestBuilder.header("Authorization", "Bearer ${it.idToken}")
        }

        return chain.proceed(requestBuilder.build())
    }
}