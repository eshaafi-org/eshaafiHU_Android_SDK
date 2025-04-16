package com.example.eshaafihu_android_sdk.core.network.networkInterceptors

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.eshaafihu_android_sdk.core.network.tokenManager.TokenManager
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Provider

internal class TokenRefreshInterceptor @Inject constructor(
    private val tokenManagerProvider: Provider<TokenManager>
) : Interceptor {

    @RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val response = chain.proceed(originalRequest)

        if (response.code == 401) {
            synchronized(this) {
                Log.e("tokenUpdate","401")
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

