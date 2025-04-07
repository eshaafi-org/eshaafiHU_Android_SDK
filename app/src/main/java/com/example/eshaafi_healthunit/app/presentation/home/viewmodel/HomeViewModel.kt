package com.example.eshaafi_healthunit.app.presentation.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eshaafihu_android_sdk.core.network.dataState.DataState
import com.example.eshaafihu_android_sdk.feature.cities.domain.usecase.CitiesUseCase
import com.example.eshaafihu_android_sdk.feature.cities.domain.entity.CitiesEntityResponseModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val citiesUseCase: CitiesUseCase
) : ViewModel() {

    private val _citiesState = MutableLiveData<DataState<CitiesEntityResponseModel>>()
    val citiesState: LiveData<DataState<CitiesEntityResponseModel>> get() = _citiesState

    fun fetchCities() {
        viewModelScope.launch {
            _citiesState.value = citiesUseCase.getCities()
        }
    }

//    fun updateSDKConfig(token: String, deviceId: String) {
//        val config = RequestConfig(
//            appVersion = "1.0.0",
//            deviceType = "Android",
//            deviceId = deviceId,
//            token = token
//        )
//        NetworkConfigManager.updateConfig(config) // ✅ SDK Gets Updated
//    }
}