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

@Module
@InstallIn(SingletonComponent::class)
internal object CitiesDiModule {

    @Provides
    @Singleton
    fun provideCitiesRepository(
        apiService: CitiesApiService
    ): CitiesRepository {
        return CitiesRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideCitiesUseCase(
        repository: CitiesRepository
    ): CitiesUseCase {
        return CitiesUseCaseImpl(repository)
    }
    @Provides
    @Singleton
    fun provideCitiesApiService(retrofit: Retrofit): CitiesApiService {
        return retrofit.create(CitiesApiService::class.java)
    }

}
