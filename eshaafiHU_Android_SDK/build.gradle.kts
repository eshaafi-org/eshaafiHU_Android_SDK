plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("dagger.hilt.android.plugin") // Explicitly add Hilt plugin
    id("kotlin-kapt") // Keep kapt for annotation processing
    id("maven-publish") // publish the SDK on github
}

android {
    namespace = "com.example.eshaafihu_android_sdk"
    compileSdk = 35

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
    buildFeatures {
        buildConfig = true // ✅ This enables BuildConfig generation
    }

    // ✅ Add flavor dimension and product flavors
    flavorDimensions += "env"

    productFlavors {
        create("dev") {
            dimension = "env"
            buildConfigField("String", "BASE_URL", "\"https://api.eshaafi.com/\"")
        }
        create("prod") {
            dimension = "env"
            buildConfigField("String", "BASE_URL", "\"https://app.eshaafi.com/\"")
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
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
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Jetpack Compose dependencies
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.material)
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
}

// ✅ Configure Maven publishing for the prodRelease variant
afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("prodRelease") {
                from(components["prodRelease"])
                groupId = "com.github.eshaafiHU_Android_SDK"
                artifactId = "eshaafiHU_Android_SDK"
                version = "1.0.3"
            }
        }
    }
}
