package com.example.eshaafihu_android_sdk.feature.cities.domain.usecase

import com.example.eshaafihu_android_sdk.core.network.dataState.DataState
import com.example.eshaafihu_android_sdk.feature.cities.domain.entity.CitiesEntityResponseModel

interface CitiesUseCase {
    suspend fun getCities(): DataState<CitiesEntityResponseModel>
}