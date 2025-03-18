package com.example.eshaafihu_android_sdk.core.network.networkUtils

import okio.IOException
import retrofit2.HttpException
import java.net.InetAddress

object NetworkUtils {

    fun isNetworkAvailable(): Boolean {
        return try {
            val address = InetAddress.getByName("8.8.8.8") // Google Public DNS
            !address.equals("")
        } catch (e: Exception) {
            false
        }
    }

    fun parseException(exception: Exception): String {
        return when (exception) {
            is HttpException -> {
                val errorBody = exception.response()?.errorBody()?.string()
                errorBody ?: "HTTP ${exception.code()} - ${exception.message()}"
            }
            is IOException -> "Network error. Please check your internet connection."
            else -> exception.localizedMessage ?: "Unknown error occurred"
        }
    }
}