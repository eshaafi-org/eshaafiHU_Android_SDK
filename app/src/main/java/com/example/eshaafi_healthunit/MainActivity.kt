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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.lifecycleScope
import com.example.eshaafi_healthunit.app.presentation.home.viewmodel.HomeViewModel
import com.example.eshaafihu_android_sdk.core.constants.Constants
import com.example.eshaafihu_android_sdk.core.network.dataState.DataState
import com.example.eshaafihu_android_sdk.feature.auth.login.data.model.OtpRequestDto
import com.example.eshaafihu_android_sdk.feature.refresh_token.data.model.RefreshTokenPost
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
        viewModel.updateSDKConfig(Constants.MY_TOKEN,"dfdsfsafasd")
        lifecycleScope.launchWhenStarted {
            viewModel.tokenFlow.collect { token ->
                Log.d("MyTOKEN", "Observed in Fragment: $token")
                // Update UI or do something with token
            }
        }
        viewModel.fetchCities()
//        viewModel.sendLoginPhone(
//            OtpRequestDto(
//                phoneNo = "+923441490027",
//                type = 1
//            )
//        )


//        viewModel.tokenRequest(
//            RefreshTokenPost(
//               deviceId = "5322",
//                type = 1,
//                uuid = "7278d7ec-feb5-4f47-bbf1-a5d8f4d271c3",
//                refreshToken = Constants.MY_TOKEN
//            )
//        )
        viewModel.citiesState.observe(this) { state ->
            when (state) {
                is DataState.Loading -> {
                    // Show loading indicator
                }
                is DataState.Success -> {
                    Log.d("tokenUpdate","${state.data}")
                    // Hide loading and display cities data
                }
                is DataState.Error -> {
                    Log.d("myResponse", state.exception)
                    // Hide loading and show error message
                }
            }
        }
        viewModel.refreshTokenRequest.observe(this) { state ->
            when (state) {
                is DataState.Loading -> {
                    // Show loading indicator
                }
                is DataState.Success -> {
                    Log.d("myResponse","${state.data.refreshToken}")
                    // Hide loading and display cities data
                }
                is DataState.Error -> {
                    Log.d("myResponse", state.exception)
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

