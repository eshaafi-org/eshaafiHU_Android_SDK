package com.example.eshaafi_healthunit

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowInsetsControllerCompat
import com.example.eshaafi_healthunit.app.presentation.home.viewmodel.HomeViewModel
import com.example.eshaafihu_android_sdk.core.network.dataState.DataState
import com.example.eshaafihu_android_sdk.feature.auth.login.data.model.OtpRequestDto
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: HomeViewModel by viewModels()
    @RequiresApi(Build.VERSION_CODES.R)
    @SuppressLint("WrongConstant")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        WindowInsetsControllerCompat(window, window.decorView).apply {
            hide(android.view.WindowInsets.Type.systemBars())
        }
//        viewModel.fetchCities()
        viewModel.sendLoginPhone(
            OtpRequestDto(
                phoneNo = "+923441490027",
                type = 1
            )
        )
        viewModel.citiesState.observe(this) { state ->
            when (state) {
                is DataState.Loading -> {
                    // Show loading indicator
                }
                is DataState.Success -> {
                    Log.d("myResponse","${state.data.response?.pakistanCities}")
                    // Hide loading and display cities data
                }
                is DataState.Error -> {
                    // Hide loading and show error message
                }
            }
        }
        viewModel.sendPhone.observe(this) { state ->
            when (state) {
                is DataState.Loading -> {
                    // Show loading indicator
                }
                is DataState.Success -> {
                    Log.d("myResponse","${state.data.response}")
                    // Hide loading and display cities data
                }
                is DataState.Error -> {
                    // Hide loading and show error message
                }
            }
        }
//        setContentView(R.layout.activity_main)
        setContent {
            MyApp()
        }
    }
//    @Preview(showBackground = true)
    @Composable
    fun MyApp() {

    }
}

