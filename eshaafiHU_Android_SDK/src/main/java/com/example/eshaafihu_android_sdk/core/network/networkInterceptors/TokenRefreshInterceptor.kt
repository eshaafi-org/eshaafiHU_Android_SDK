package com.example.eshaafihu_android_sdk.core.network.networkInterceptors

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.eshaafihu_android_sdk.core.app_logger.AppLogger
import com.example.eshaafihu_android_sdk.core.network.tokenManager.TokenManager
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Provider


/**
 * An OkHttp interceptor that handles token refresh when a 401 Unauthorized response is received.
 * This interceptor checks the response code for 401 errors, indicating that the token is expired,
 * and attempts to refresh the token and retry the request with the new token.
 *
 * @param tokenManagerProvider A provider for the [TokenManager] that is used to refresh the token.
 */

internal class TokenRefreshInterceptor @Inject constructor(
    private val tokenManagerProvider: Provider<TokenManager>
) : Interceptor {

    /**
     * Intercepts the HTTP request and response. If a 401 Unauthorized response is received,
     * it attempts to refresh the authentication token and retry the request with the new token.
     * The token refresh operation is performed synchronously using [runBlocking] to ensure the new token
     * is retrieved before proceeding with the request.
     *
     * @param chain The interceptor chain that provides access to the original request and response.
     * @return [Response] The response from the network after refreshing the token and retrying the request.
     * @throws IOException If there is an issue with the network request or response.
     */

    @RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val response = chain.proceed(originalRequest)

        if (response.code == 401) {
            synchronized(this) {
                AppLogger.e(message = "TokenExpired=${response.code.toString()}")
                val newToken = runBlocking {
                    tokenManagerProvider.get().refreshToken().refreshToken.idToken
                }
                val newRequest = originalRequest.newBuilder()
                    .header("Authorization", "Bearer $newToken")
                    .build()

                response.close()
                return chain.proceed(newRequest)
            }
        }

        return response
    }
}

