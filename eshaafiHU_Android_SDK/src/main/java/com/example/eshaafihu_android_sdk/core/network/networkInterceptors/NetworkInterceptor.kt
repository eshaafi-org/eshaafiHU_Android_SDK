package com.example.eshaafihu_android_sdk.core.network.networkInterceptors

import com.example.eshaafihu_android_sdk.core.network.networkUtils.NetworkUtils
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

internal class NetworkInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!NetworkUtils.isNetworkAvailable()) {
            throw NoConnectivityException()
        }
        return chain.proceed(chain.request())
    }
}

class NoConnectivityException : IOException("No Internet Connection")