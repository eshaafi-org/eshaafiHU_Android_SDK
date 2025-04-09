package com.example.eshaafi_healthunit.app.presentation.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eshaafihu_android_sdk.core.network.dataState.DataState
import com.example.eshaafihu_android_sdk.core.network.networkConfiguration.NetworkConfigManager
import com.example.eshaafihu_android_sdk.core.network.networkConfiguration.RequestConfig
import com.example.eshaafihu_android_sdk.feature.auth.login.data.model.OtpRequestDto
import com.example.eshaafihu_android_sdk.feature.auth.login.domain.entity.OtpRequestResponse
import com.example.eshaafihu_android_sdk.feature.auth.login.domain.usecases.LoginUseCases
import com.example.eshaafihu_android_sdk.feature.cities.domain.usecase.CitiesUseCase
import com.example.eshaafihu_android_sdk.feature.cities.domain.entity.CitiesEntityResponseModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val citiesUseCase: CitiesUseCase,
    private val loginUseCase: LoginUseCases
) : ViewModel() {

    private val _citiesState = MutableLiveData<DataState<CitiesEntityResponseModel>>()
    val citiesState: LiveData<DataState<CitiesEntityResponseModel>> get() = _citiesState

    private val _sendPhone = MutableLiveData<DataState<OtpRequestResponse>>()
    val sendPhone: LiveData<DataState<OtpRequestResponse>> get() = _sendPhone

    fun fetchCities() {
        viewModelScope.launch {
            _citiesState.value = citiesUseCase.getCities()
        }
    }
    fun sendLoginPhone(request:OtpRequestDto) {
        viewModelScope.launch {
            _sendPhone.value = loginUseCase.loginResponse(request)
        }
    }

    fun updateSDKConfig(token: String, deviceId: String) {
        val config = RequestConfig(
            appVersion = "1.0.0",
            deviceType = "Android",
            deviceId = deviceId,
            token = token
        )
        NetworkConfigManager.updateConfig(config) // ✅ SDK Gets Updated
    }
}