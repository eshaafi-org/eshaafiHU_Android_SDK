package com.example.eshaafi_healthunit.app.presentation.home.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eshaafihu_android_sdk.core.network.DataState
import com.example.eshaafihu_android_sdk.core.usecase.CitiesUseCase
import com.example.eshaafihu_android_sdk.feature.cities.data.model.CitiesResponseModelDto
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val citiesUseCase: CitiesUseCase
) : ViewModel() {

    private val _citiesState = MutableLiveData<DataState<CitiesResponseModelDto>>()
    val citiesState: LiveData<DataState<CitiesResponseModelDto>> get() = _citiesState

    fun fetchCities() {
        viewModelScope.launch {
            _citiesState.value = citiesUseCase.getCities()
        }
    }
}