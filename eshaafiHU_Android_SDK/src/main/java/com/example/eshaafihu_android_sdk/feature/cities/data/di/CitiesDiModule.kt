package com.example.eshaafihu_android_sdk.feature.cities.data.di

import com.example.eshaafihu_android_sdk.feature.cities.data.apiService.CitiesApiService
import com.example.eshaafihu_android_sdk.feature.cities.data.repository.CitiesRepositoryImpl
import com.example.eshaafihu_android_sdk.core.usecase.CitiesUseCase
import com.example.eshaafihu_android_sdk.feature.cities.domain.repository.CitiesRepository
import com.example.eshaafihu_android_sdk.feature.cities.domain.usecase.CitiesUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CitiesDiModule {

    @Binds
    @Singleton
    abstract fun bindCitiesRepository(
        repositoryImpl: CitiesRepositoryImpl
    ): CitiesRepository

    @Binds
    @Singleton
    abstract fun bindCitiesUseCase(
        useCaseImpl: CitiesUseCaseImpl
    ): CitiesUseCase

    companion object {
        @Provides
        @Singleton
        fun provideCitiesApiService(retrofit: Retrofit): CitiesApiService {
            return retrofit.create(CitiesApiService::class.java)
        }
    }
}
