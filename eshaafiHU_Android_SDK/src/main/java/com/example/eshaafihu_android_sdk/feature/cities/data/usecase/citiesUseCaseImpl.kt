package com.example.eshaafihu_android_sdk.feature.cities.data.usecase

import com.example.eshaafihu_android_sdk.core.network.dataState.DataState
import com.example.eshaafihu_android_sdk.feature.cities.domain.entity.CitiesEntityResponseModel
import com.example.eshaafihu_android_sdk.feature.cities.domain.usecase.CitiesUseCase
import com.example.eshaafihu_android_sdk.feature.cities.domain.repository.CitiesRepository
import javax.inject.Inject

class CitiesUseCaseImpl @Inject constructor(
    private val repository: CitiesRepository
) : CitiesUseCase {
    override suspend fun getCities(): DataState<CitiesEntityResponseModel> {
        return repository.getCities()
    }
}