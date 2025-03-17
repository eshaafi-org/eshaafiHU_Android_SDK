package com.example.eshaafihu_android_sdk.feature.cities.data.DI

import com.example.eshaafihu_android_sdk.feature.cities.data.apiService.CitiesApiService
import com.example.eshaafihu_android_sdk.feature.cities.data.repository.CitiesRepositoryImpl
import com.example.eshaafihu_android_sdk.feature.cities.domain.repository.CitiesRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CitiesModule {

    @Binds
    @Singleton
    abstract fun bindCitiesRepository(
        repositoryImpl: CitiesRepositoryImpl
    ): CitiesRepository

    companion object {
        @Provides
        @Singleton
        fun provideCitiesApiService(retrofit: Retrofit): CitiesApiService {
            return retrofit.create(CitiesApiService::class.java)
        }
    }
}
