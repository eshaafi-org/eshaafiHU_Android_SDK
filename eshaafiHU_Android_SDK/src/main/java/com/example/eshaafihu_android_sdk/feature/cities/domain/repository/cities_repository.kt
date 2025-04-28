package com.example.eshaafihu_android_sdk.feature.cities.domain.repository

import com.example.eshaafihu_android_sdk.core.network.dataState.DataState
import com.example.eshaafihu_android_sdk.feature.cities.domain.entity.CitiesEntityResponseModel

/**
 * The `CitiesRepository` interface defines the contract for fetching city-related data. It serves as the data layer
 * for the Cities feature in the app. The interface provides methods for retrieving city data from a data source,
 * whether it be a network API, database, or other storage mechanisms.
 *
 * Implementations of this interface will define the logic for retrieving city data.
 */

interface CitiesRepository {
    /**
     * Fetches a list of cities.
     *
     * This method is responsible for retrieving the city data. The data can come from different sources
     * such as a network API or a local database, depending on the implementation.
     *
     * @return A `DataState` object containing the result of the operation. This can either be a
     *         `Success` containing the `CitiesEntityResponseModel` or an `Error` if the operation fails.
     */

    suspend fun getCities(): DataState<CitiesEntityResponseModel>
}