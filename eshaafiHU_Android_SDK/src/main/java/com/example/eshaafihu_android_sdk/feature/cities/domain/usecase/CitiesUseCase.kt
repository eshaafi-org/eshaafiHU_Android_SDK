package com.example.eshaafihu_android_sdk.feature.cities.domain.usecase

import com.example.eshaafihu_android_sdk.core.network.dataState.DataState
import com.example.eshaafihu_android_sdk.feature.cities.domain.entity.CitiesEntityResponseModel

/**
 * The `CitiesUseCase` interface defines the contract for the business logic related to fetching city data.
 * It acts as the intermediary between the repository layer and the presentation layer in the app.
 *
 * Implementations of this interface should handle any business logic or transformation before data is
 * returned to the presentation layer (e.g., filtering, sorting) and should call methods from the repository layer
 * to retrieve the necessary data.
 */

interface CitiesUseCase {

    /**
     * Retrieves a list of cities.
     *
     * This method acts as a use case for fetching city data. It calls the `CitiesRepository` to fetch the data
     * and handles any business logic before returning the result.
     *
     * @return A `DataState` object containing the result of the operation. It can either be a
     *         `Success` with the `CitiesEntityResponseModel` or an `Error` in case of failure.
     */

    suspend fun getCities(): DataState<CitiesEntityResponseModel>
}