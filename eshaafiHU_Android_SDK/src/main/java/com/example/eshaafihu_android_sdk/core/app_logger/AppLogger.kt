package com.example.eshaafihu_android_sdk.core.app_logger


import android.util.Log
import com.example.eshaafihu_android_sdk.BuildConfig

/**
 * Logger utility to print logs only in the development (debug) environment.
 */
object AppLogger {

    private const val DEFAULT_TAG = "eshaafiHU"

    /**
     * Logs debug messages if in DEBUG build.
     *
     * @param tag Custom tag for the log.
     * @param message The message to log.
     */
    fun d(tag: String = DEFAULT_TAG, message: String) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, message)
        }
    }

    /**
     * Logs error messages if in DEBUG build.
     *
     * @param tag Custom tag for the log.
     * @param message The message to log.
     */
    fun e(tag: String = DEFAULT_TAG, message: String) {
        if (BuildConfig.DEBUG) {
            Log.e(tag, message)
        }
    }
}
