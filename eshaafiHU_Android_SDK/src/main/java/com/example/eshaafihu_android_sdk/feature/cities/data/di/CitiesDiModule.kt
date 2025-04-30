package com.example.eshaafihu_android_sdk.feature.cities.data.di

import com.example.eshaafihu_android_sdk.feature.cities.data.apiService.CitiesApiService
import com.example.eshaafihu_android_sdk.feature.cities.data.repository.CitiesRepositoryImpl
import com.example.eshaafihu_android_sdk.feature.cities.domain.usecase.CitiesUseCase
import com.example.eshaafihu_android_sdk.feature.cities.domain.repository.CitiesRepository
import com.example.eshaafihu_android_sdk.feature.cities.data.usecase.CitiesUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


/**
 * The `CitiesDiModule` is a Dagger module that provides dependencies related to the Cities feature.
 * It is responsible for providing the necessary components for the Cities feature, such as the
 * repository, use case, and API service, through Dagger's dependency injection framework.
 *
 * This module ensures that all dependencies for interacting with the Cities API are injected
 * into the relevant parts of the application, maintaining a clean and modular architecture.
 */

@Module
@InstallIn(SingletonComponent::class)
internal object CitiesDiModule {

    /**
     * Provides the `CitiesRepository` implementation.
     *
     * This method provides an instance of the repository that handles the data operations for cities.
     * It uses the `CitiesApiService` to fetch data from the API and transforms it into the domain models.
     *
     * @param apiService The API service that communicates with the backend to retrieve city data.
     * @return An instance of `CitiesRepository`, which abstracts data operations related to cities.
     */

    @Provides
    @Singleton
    fun provideCitiesRepository(
        apiService: CitiesApiService
    ): CitiesRepository {
        return CitiesRepositoryImpl(apiService)
    }

    /**
     * Provides the `CitiesUseCase` implementation.
     *
     * This method provides the use case for cities, which is responsible for handling business logic
     * related to cities. The use case layer abstracts the interaction between the repository and
     * the UI or other components.
     *
     * @param repository The repository that contains the data fetching logic for cities.
     * @return An instance of `CitiesUseCase`, which provides the business logic for cities.
     */

    @Provides
    @Singleton
    fun provideCitiesUseCase(
        repository: CitiesRepository
    ): CitiesUseCase {
        return CitiesUseCaseImpl(repository)
    }

    /**
     * Provides the `CitiesApiService` instance used to make API calls.
     *
     * This method provides an instance of the `CitiesApiService`, which defines the API endpoints
     * for interacting with the backend. The `CitiesApiService` is used for fetching city-related data.
     *
     * @param retrofit The Retrofit instance used to create the API service.
     * @return An instance of `CitiesApiService`, which contains the methods for interacting with the API.
     */

    @Provides
    @Singleton
    fun provideCitiesApiService(retrofit: Retrofit): CitiesApiService {
        return retrofit.create(CitiesApiService::class.java)
    }

}
