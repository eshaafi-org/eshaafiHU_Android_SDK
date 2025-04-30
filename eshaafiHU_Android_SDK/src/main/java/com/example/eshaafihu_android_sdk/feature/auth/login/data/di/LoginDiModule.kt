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

/**
 * The `LoginDiModule` is a Dagger-Hilt module that provides dependencies for the login functionality.
 * It defines the bindings for the repository, use case, and API service required for phone-based login (OTP).
 */

@Module
@InstallIn(SingletonComponent::class)
internal object LoginDiModule {

    /**
     * Provides the [LoginRepository] dependency.
     *
     * This function creates and provides an instance of [LoginRepositoryImp], which implements the [LoginRepository] interface.
     * It requires an instance of [OtpRequestApi] to interact with the backend OTP login API.
     *
     * @param apiService The [OtpRequestApi] to be used by the repository for making network requests.
     * @return An instance of [LoginRepository] implementation, specifically [LoginRepositoryImp].
     */

    @Provides
    @Singleton
    fun provideLoginRepository(
        apiService: OtpRequestApi
    ): LoginRepository {
        return LoginRepositoryImp(apiService)
    }


    /**
     * Provides the [LoginUseCases] dependency.
     *
     * This function creates and provides an instance of [LoginUseCasesImpl], which implements the [LoginUseCases] interface.
     * It requires an instance of [LoginRepository] to perform the login-related operations.
     *
     * @param repository The [LoginRepository] to be used by the use case for data retrieval and processing.
     * @return An instance of [LoginUseCases] implementation, specifically [LoginUseCasesImpl].
     */

    @Provides
    @Singleton
    fun provideLoginUseCase(
        repository: LoginRepository
    ): LoginUseCases {
        return LoginUseCasesImpl(repository)
    }

    /**
     * Provides the [OtpRequestApi] dependency.
     *
     * This function creates and provides an instance of the [OtpRequestApi] using the provided [Retrofit] instance.
     * The [OtpRequestApi] is used to interact with the OTP login API on the backend.
     *
     * @param retrofit The [Retrofit] instance used to create the [OtpRequestApi] service.
     * @return An instance of [OtpRequestApi] for making network requests related to OTP login.
     */

    @Provides
    @Singleton
    fun provideLoginApiService(retrofit: Retrofit): OtpRequestApi {
        return retrofit.create(OtpRequestApi::class.java)
    }

}