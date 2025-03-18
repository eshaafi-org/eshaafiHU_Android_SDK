package com.example.eshaafihu_android_sdk.feature.cities.domain.repository

import com.example.eshaafihu_android_sdk.core.network.dataState.DataState
import com.example.eshaafihu_android_sdk.feature.cities.data.model.CitiesResponseModelDto

interface CitiesRepository {
    suspend fun getCities(): DataState<CitiesResponseModelDto>
}