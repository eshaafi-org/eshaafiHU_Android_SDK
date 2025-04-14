package com.example.eshaafihu_android_sdk.feature.auth.login.data.di

import com.example.eshaafihu_android_sdk.feature.auth.login.data.apiService.OtpRequestApi
import com.example.eshaafihu_android_sdk.feature.auth.login.data.repository.LoginRepositoryImp
import com.example.eshaafihu_android_sdk.feature.auth.login.data.usecases.LoginUseCasesImpl
import com.example.eshaafihu_android_sdk.feature.auth.login.domain.repository.LoginRepository
import com.example.eshaafihu_android_sdk.feature.auth.login.domain.usecases.LoginUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object LoginDiModule {

    @Provides
    @Singleton
    fun provideLoginRepository(
        apiService: OtpRequestApi
    ): LoginRepository {
        return LoginRepositoryImp(apiService)
    }

    @Provides
    @Singleton
    fun provideLoginUseCase(
        repository: LoginRepository
    ): LoginUseCases {
        return LoginUseCasesImpl(repository)
    }

    @Provides
    @Singleton
    fun provideLoginApiService(retrofit: Retrofit): OtpRequestApi {
        return retrofit.create(OtpRequestApi::class.java)
    }

}