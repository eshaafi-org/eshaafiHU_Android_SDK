package com.example.eshaafi_healthunit.app.data.home.di

import com.example.eshaafi_healthunit.app.data.home.api.HomeApi
import com.example.eshaafi_healthunit.app.data.home.repository.HomeRepositoryImpl
import com.example.eshaafi_healthunit.app.domain.home.repository.HomeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class) // This should not be SingletonComponent
object HomeModule {

    @Provides
    fun provideHomeRepository(homeApi: HomeApi): HomeRepository {
        return HomeRepositoryImpl(homeApi)
    }
}

