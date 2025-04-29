package com.example.eshaafihu_android_sdk.feature.cities.data.usecase

import com.example.eshaafihu_android_sdk.core.app_logger.AppLogger
import com.example.eshaafihu_android_sdk.core.network.dataState.DataState
import com.example.eshaafihu_android_sdk.feature.cities.domain.entity.CitiesEntityResponseModel
import com.example.eshaafihu_android_sdk.feature.cities.domain.usecase.CitiesUseCase
import com.example.eshaafihu_android_sdk.feature.cities.domain.repository.CitiesRepository
import javax.inject.Inject


/**
 * The `CitiesUseCaseImpl` is an implementation of the `CitiesUseCase` interface. It acts as the business logic layer
 * for the Cities feature in the app. The use case interacts with the `CitiesRepository` to fetch city-related data
 * and returns the results as domain models.
 *
 * This class abstracts the logic for retrieving cities and provides it in a way that is suitable for the UI
 * or other components in the app to consume.
 */

internal class CitiesUseCaseImpl @Inject constructor(
    private val repository: CitiesRepository
) : CitiesUseCase {

    /**
     * Retrieves a list of cities by calling the repository's `getCities` method.
     *
     * This method serves as the business logic layer for fetching city data. It delegates the responsibility
     * of interacting with the repository to the `getCities` method of `CitiesRepository`.
     *
     * @return A `DataState` object representing the result of the operation. This can either be a `Success`
     *         containing the `CitiesEntityResponseModel` or an `Error` if the operation fails.
     */

    override suspend fun getCities(): DataState<CitiesEntityResponseModel> {
        AppLogger.d(message = "UseCase: getCities() called")
        val result = repository.getCities()

        when (result) {
            is DataState.Success -> AppLogger.d(message = "UseCase: getCities() success with ${result.data}")
            is DataState.Error -> AppLogger.e(message = "UseCase: getCities() failed with ${result.exception}")
            DataState.Loading -> TODO()
        }
        return repository.getCities()
    }
}