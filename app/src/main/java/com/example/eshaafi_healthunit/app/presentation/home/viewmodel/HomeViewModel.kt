package com.example.eshaafi_healthunit.app.presentation.home.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.eshaafi_healthunit.app.domain.home.model.CityStatus
import com.example.eshaafi_healthunit.app.domain.home.model.HomeModel
import com.example.eshaafi_healthunit.app.domain.home.usecases.GetItemsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import kotlinx.coroutines.launch


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getItemsUseCase: GetItemsUseCase
) : ViewModel() {
//    private val _items = MutableStateFlow<CityStatus?>(null) // Use nullable type
//    val items: LiveData<CityStatus?> = _items.asLiveData()

    private val _items = MutableLiveData<CityStatus>()
    val items: LiveData<CityStatus> = _items

    fun fetchItems() {
        viewModelScope.launch {
            try {
                _items.value = getItemsUseCase()
            } catch (e: Exception) {
                Log.e("HomeViewModel", "$e")
            }
        }
    }
}




