package com.example.eshaafihu_android_sdk.feature.cities.data.apiService

import com.example.eshaafihu_android_sdk.core.constants.Constants.Companion.CITIES_API
import com.example.eshaafihu_android_sdk.core.constants.Constants.Companion.GET_PROFILE
import com.example.eshaafihu_android_sdk.feature.cities.data.model.CitiesResponseModelDto
import retrofit2.Response
import retrofit2.http.GET

/**
 * The `CitiesApiService` interface defines the API endpoints for fetching city data.
 * It is used to interact with the backend service to retrieve the list of cities.
 *
 * The service interface abstracts the API layer, allowing you to call network requests
 * to fetch city-related information in a structured manner.
 */

internal interface CitiesApiService {

    /**
     * Fetches a list of cities from the remote server.
     *
     * This method makes a network request to the [CITIES_API] endpoint to retrieve a response
     * containing a list of cities. The response is encapsulated in a [Response] object that contains
     * a [CitiesResponseModelDto], which holds the actual list of cities.
     *
     * @return A [Response] object that contains the result of the API request.
     *         On success, the [CitiesResponseModelDto] contains the list of cities.
     */

    @GET(CITIES_API) // Update this if the actual endpoint is different
    suspend fun getCities(): Response<CitiesResponseModelDto>
}