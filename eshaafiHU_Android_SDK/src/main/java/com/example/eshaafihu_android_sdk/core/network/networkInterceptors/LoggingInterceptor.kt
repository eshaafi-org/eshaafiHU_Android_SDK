package com.example.eshaafihu_android_sdk.core.network.networkInterceptors

import okhttp3.Interceptor
import okhttp3.Response
import org.json.JSONObject
import org.json.JSONArray
import java.io.IOException
import java.nio.charset.Charset
import java.util.concurrent.TimeUnit

/**
 * Interceptor for logging HTTP request and response details in a readable and formatted manner.
 * This class intercepts network requests and responses to log them, including request method, headers, body content,
 * response status code, headers, and formatted body content (if applicable).
 *
 * @property chain The interceptor chain that allows modification of the request before proceeding with the network call.
 */

internal class PrettyLoggingInterceptor : Interceptor {

    /**
     * Intercepts the HTTP request and logs detailed information about the request and response.
     * This includes request method, URL, headers, and body, as well as response code, headers, and formatted body.
     * The time taken for the network call is also logged.
     *
     * @param chain The interceptor chain that provides access to the original request.
     * @return [Response] The response from the network after processing the request.
     * @throws IOException If a network error occurs, the exception is logged and re-thrown.
     */
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


    /**
     * Formats a JSON string with indentation for better readability.
     *
     * @param json The raw JSON string to be formatted.
     * @return A formatted version of the JSON string if it is valid JSON, otherwise the original string.
     */

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
