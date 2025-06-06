package com.example.eshaafihu_android_sdk.feature.cities.data.repository

import com.example.eshaafihu_android_sdk.core.app_logger.AppLogger
import com.example.eshaafihu_android_sdk.core.network.dataState.DataState
import com.example.eshaafihu_android_sdk.core.network.networkUtils.NetworkUtils
import com.example.eshaafihu_android_sdk.feature.cities.data.apiService.CitiesApiService
import com.example.eshaafihu_android_sdk.feature.cities.domain.entity.CitiesEntityResponseModel
import com.example.eshaafihu_android_sdk.feature.cities.domain.repository.CitiesRepository
import javax.inject.Inject



/**
 * Implementation of [CitiesRepository] responsible for fetching cities data from the API
 * and mapping the response from DTO (Data Transfer Object) to domain models.
 *
 * @param apiService The API service used to fetch cities data.
 */

internal class CitiesRepositoryImpl @Inject constructor(
    private val apiService: CitiesApiService
) : CitiesRepository {

    /**
     * Fetches the list of cities from the API.
     * Converts the API response from DTO to domain model before returning.
     *
     * @return [DataState] containing either the successfully fetched [CitiesEntityResponseModel]
     * or an error message in case of failure.
     */

    override suspend fun getCities(): DataState<CitiesEntityResponseModel> {
        AppLogger.d(message = "Fetching cities from API")
        return try {
            val response = apiService.getCities()
            if (response.isSuccessful && response.body() != null) {
                val domainModel = response.body()!!.toDomain()  // ✅ Convert DTO to Domain Model
                AppLogger.d(message = "API Success: $domainModel")
                DataState.Success(domainModel)
            } else {
                AppLogger.e(message = "API Error: ${response.message()}")
                DataState.Error(response.code().toString() ?: "Unknown error")
            }
        } catch (e: Exception) {
            AppLogger.e(message = "API Exception: ${NetworkUtils.parseException(e)}")
            DataState.Error(NetworkUtils.parseException(e))
        }
    }
}

