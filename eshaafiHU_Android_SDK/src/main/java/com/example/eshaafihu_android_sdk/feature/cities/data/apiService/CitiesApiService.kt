package com.example.eshaafihu_android_sdk.feature.cities.data.apiService

import com.example.eshaafihu_android_sdk.core.constants.Constants.Companion.CITIES_API
import com.example.eshaafihu_android_sdk.feature.cities.data.model.CitiesResponseModelDto
import retrofit2.Response
import retrofit2.http.GET

internal interface CitiesApiService {
    @GET(CITIES_API) // Update this if the actual endpoint is different
    suspend fun getCities(): Response<CitiesResponseModelDto>
}