package com.example.eshaafihu_android_sdk.feature.cities.domain.usecase

import com.example.eshaafihu_android_sdk.core.network.DataState
import com.example.eshaafihu_android_sdk.core.usecase.BaseUseCase
import com.example.eshaafihu_android_sdk.feature.cities.data.model.CitiesResponseModelDto
import com.example.eshaafihu_android_sdk.feature.cities.domain.repository.CitiesRepository
import javax.inject.Inject

class CitiesUseCaseImpl @Inject constructor(
    private val repository: CitiesRepository
) : BaseUseCase<DataState<CitiesResponseModelDto>, Unit>() {

    override suspend fun execute(params: Unit): DataState<CitiesResponseModelDto> {
        return repository.getCities()
    }
}