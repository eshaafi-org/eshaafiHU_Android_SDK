package com.example.eshaafihu_android_sdk.core.network.networkInterceptors

import okhttp3.Interceptor
import okhttp3.Response
import org.json.JSONObject
import org.json.JSONArray
import java.io.IOException
import java.nio.charset.Charset
import java.util.concurrent.TimeUnit

internal class PrettyLoggingInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val startTime = System.nanoTime()

        // Log Request Information
        val requestLog = buildString {
            append("➡️ Request: ${request.method} ${request.url}\n")
            append("Headers:\n")
            request.headers.forEach { append("  ${it.first}: ${it.second}\n") }
            request.body?.let {
                val buffer = okio.Buffer()
                it.writeTo(buffer)
                val bodyString = buffer.readString(Charset.forName("UTF-8"))
                append("Body:\n$bodyString\n")
            }
        }
        println(requestLog)

        val response = try {
            chain.proceed(request)
        } catch (e: IOException) {
            println("❌ Network Error: ${e.message}")
            throw e
        }

        val endTime = System.nanoTime()
        val durationMs = TimeUnit.NANOSECONDS.toMillis(endTime - startTime)

        // Log Response Information
        val responseLog = buildString {
            append("⬅️ Response: ${response.code} (${response.message}) ${request.url} in ${durationMs}ms\n")
            append("Headers:\n")
            response.headers.forEach { append("  ${it.first}: ${it.second}\n") }

            response.body?.let { responseBody ->
                val source = responseBody.source()
                source.request(Long.MAX_VALUE)
                val buffer = source.buffer
                val bodyString = buffer.clone().readString(Charset.forName("UTF-8"))
                append("Body:\n${formatJson(bodyString)}\n")
            }
        }
        println(responseLog)

        return response
    }

    private fun formatJson(json: String): String {
        return try {
            when {
                json.startsWith("{") -> JSONObject(json).toString(4)
                json.startsWith("[") -> JSONArray(json).toString(4)
                else -> json
            }
        } catch (e: Exception) {
            json
        }
    }
}
