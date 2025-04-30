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
import androidx.compose.runtime.LaunchedEffect
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.eshaafi_healthunit.app.presentation.home.viewmodel.HomeViewModel
import com.example.eshaafihu_android_sdk.core.app_logger.AppLogger
import com.example.eshaafihu_android_sdk.core.constants.Constants
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
//        viewModel.updateSDKConfig(Constants.MY_TOKEN,"dfdsfsafasd")
//        lifecycleScope.launchWhenStarted {
//            viewModel.tokenFlow.collect { token ->
//                AppLogger.d(message = "Observed token in Ui: $token")
//                viewModel.updateAuthConfig(
//                    token?.refreshToken?.idToken.toString(),
//                    token?.refreshToken?.accessToken.toString(),
//                    token?.refreshToken?.refreshToken.toString()
//                )
//                // Update UI or do something with token
//            }
//        }
//        viewModel.fetchCities()
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
        viewModel.citiesState.observeOnce(this) { state ->
            when (state) {
                is DataState.Loading -> {
                    // Show loading indicator
                }
                is DataState.Success -> {
                    AppLogger.d(message = "All Cities=${state.data}")
                    // Hide loading and display cities data
                }
                is DataState.Error -> {
                    AppLogger.d(message = "Exception=${state.exception}")
                    // Hide loading and show error message
                }
            }
        }
        viewModel.refreshTokenRequest.observeOnce(this) { state ->
            when (state) {
                is DataState.Loading -> {
                    // Show loading indicator
                }
                is DataState.Success -> {
//                    AppLogger.d(message = "token request response=${state.data.refreshToken}")
                    // Hide loading and display cities data
                }
                is DataState.Error -> {
                    // Hide loading and show error message
                }
            }
        }
        viewModel.sendPhone.observeOnce(this) { state ->
            when (state) {
                is DataState.Loading -> {
                    // Show loading indicator
                }
                is DataState.Success -> {
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
    LaunchedEffect(Unit) {
//       viewModel.fetchCities()
        viewModel.sendLoginPhone(
            OtpRequestDto(
                phoneNo = "+923441490027",
                type = 1
            )
        )
    }
    }
}

fun <T> LiveData<T>.observeOnce(lifecycleOwner: LifecycleOwner, observer: Observer<T>) {
    observe(lifecycleOwner, object : Observer<T> {
        override fun onChanged(value: T) {
            observer.onChanged(value)
            removeObserver(this) // Remove observer after first callback
        }
    })
}


