package com.example.eshaafihu_android_sdk.core.usecase

import com.example.eshaafihu_android_sdk.core.network.DataState
import com.example.eshaafihu_android_sdk.feature.cities.data.model.CitiesResponseModelDto

interface CitiesUseCase {
    suspend fun getCities(): DataState<CitiesResponseModelDto>
}