package com.example.eshaafihu_android_sdk.core.network.networkUtils

import okio.IOException
import retrofit2.HttpException
import java.net.InetAddress

/**
 * A utility object that provides helper functions related to network operations.
 * It includes methods for checking network availability and parsing exceptions that occur
 * during network operations.
 *
 * @object NetworkUtils
 */

internal object NetworkUtils {

    /**
     * Checks whether the device has an active network connection.
     * This method attempts to reach Google's public DNS (8.8.8.8) to determine network availability.
     * If the device can connect to the DNS, it is considered that the network is available.
     *
     * @return `true` if the device has network access, otherwise `false`.
     */

    fun isNetworkAvailable(): Boolean {
        return try {
            val address = InetAddress.getByName("8.8.8.8") // Google Public DNS
            !address.equals("")
        } catch (e: Exception) {
            false
        }
    }

    /**
     * Parses exceptions that occur during network operations and returns a human-readable error message.
     * This method handles common exceptions like [HttpException] and [IOException] and returns
     * a custom error message based on the exception type.
     *
     * @param exception The exception that occurred during the network operation.
     * @return A string message that describes the error based on the exception.
     */

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