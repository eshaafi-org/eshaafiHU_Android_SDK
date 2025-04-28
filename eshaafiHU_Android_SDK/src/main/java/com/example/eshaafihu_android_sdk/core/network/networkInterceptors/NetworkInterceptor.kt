package com.example.eshaafihu_android_sdk.core.network.networkInterceptors

import com.example.eshaafihu_android_sdk.core.network.networkUtils.NetworkUtils
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * An OkHttp interceptor that checks for network availability before proceeding with the network request.
 * If the network is unavailable, it throws a [NoConnectivityException].
 * This interceptor ensures that no network requests are made when the device is offline.
 *
 * @property chain The interceptor chain that allows modification of the request and handling of the response.
 */

internal class NetworkInterceptor() : Interceptor {

    /**
     * Intercepts the HTTP request and checks if the network is available.
     * If the network is unavailable, a [NoConnectivityException] is thrown.
     *
     * @param chain The interceptor chain that provides access to the original request and response.
     * @return [Response] The response from the network after processing the intercepted request.
     * @throws NoConnectivityException If there is no network connection available.
     */

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!NetworkUtils.isNetworkAvailable()) {
            throw NoConnectivityException()
        }
        return chain.proceed(chain.request())
    }
}

/**
 * Custom exception that is thrown when there is no network connectivity available.
 * This exception is used in conjunction with [NetworkInterceptor] to indicate that
 * network requests cannot proceed due to lack of an internet connection.
 *
 * @param message The message that provides details about the exception.
 */

class NoConnectivityException : IOException("No Internet Connection")