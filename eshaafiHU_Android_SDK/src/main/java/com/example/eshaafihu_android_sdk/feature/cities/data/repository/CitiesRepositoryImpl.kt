package com.example.eshaafihu_android_sdk.feature.cities.data.repository

import com.example.eshaafihu_android_sdk.core.network.dataState.DataState
import com.example.eshaafihu_android_sdk.core.network.networkUtils.NetworkUtils
import com.example.eshaafihu_android_sdk.feature.cities.data.apiService.CitiesApiService
import com.example.eshaafihu_android_sdk.feature.cities.data.model.CitiesResponseModelDto
import com.example.eshaafihu_android_sdk.feature.cities.domain.repository.CitiesRepository
import javax.inject.Inject

class CitiesRepositoryImpl @Inject constructor(
    private val apiService: CitiesApiService
) : CitiesRepository {
    override suspend fun getCities(): DataState<CitiesResponseModelDto> {
        return try {
            val response = apiService.getCities()
            if (response.isSuccessful && response.body() != null) {
                DataState.Success(response.body()!!)
            } else {
                DataState.Error(response.message() ?: "Unknown error")
            }
        } catch (e: Exception) {
            DataState.Error(NetworkUtils.parseException(e))
        }
    }
}
