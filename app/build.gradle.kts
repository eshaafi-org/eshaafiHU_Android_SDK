plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("dagger.hilt.android.plugin") // Explicitly add Hilt plugin
    id("kotlin-kapt") // Keep kapt for annotation processing
}

android {
    namespace = "com.example.eshaafi_healthunit"
    compileSdk = 35

    // Generate debug.keystore if it doesn't exist in custom path
//    val debugKeystore = File("/home/terafort/Documents/eshaafiHU_Android_SDK/app/debug.keystore")
//    if (!debugKeystore.exists()) {
//        debugKeystore.parentFile.mkdirs()
//        exec {
//            commandLine(
//                "keytool", "-genkey", "-v",
//                "-keystore", debugKeystore.absolutePath,
//                "-storepass", "android",
//                "-alias", "androiddebugkey",
//                "-keypass", "android",
//                "-keyalg", "RSA", "-keysize", "2048", "-validity", "10000",
//                "-dname", "CN=Android Debug,O=Android,C=US"
//            )
//        }
//    }



    defaultConfig {
        applicationId = "com.example.eshaafi_healthunit"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    // ✅ Add these lines to fix SDK flavor matching
    flavorDimensions += "env"
    productFlavors {
        create("dev") {
            dimension = "env"
            matchingFallbacks += listOf("dev") // Required for SDK variant resolution
        }
        create("prod") {
            dimension = "env"
            matchingFallbacks += listOf("prod")
        }
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14"
    }

    signingConfigs {
        create("debugKey") {
            storeFile = file("debug.keystore")
            storePassword = "android"
            keyAlias = "androiddebugkey"
            keyPassword = "android"
        }
    }



    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            // 👇 Use debug key for release build (for internal testing only)
            signingConfig = signingConfigs.getByName("debugKey")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }


}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.ui.graphics)

    // Jetpack Compose dependencies
    implementation(libs.androidx.compose.ui)
//    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.compose.tooling.preview)
    implementation(libs.androidx.compose.viewmodel)
    implementation(libs.androidx.compose.activity)
    debugImplementation(libs.androidx.compose.tooling)
    implementation(libs.androidx.compose.compiler)


    implementation(libs.androidx.compose.navigation)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    // Retrofit & OkHttp
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging)

    // Hilt for DI
    implementation(libs.hilt)
    kapt(libs.hilt.compiler)
    kapt(libs.androidx.hilt.compiler)

    // ViewModel (if not already added)
    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.androidx.lifecycle.livedata)

    implementation(libs.coroutines)

    implementation(project(":eshaafiHU_Android_SDK"))
}