plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    kotlin("plugin.serialization") version "2.0.10"
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.tech.mymedicalstoreadminapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.tech.mymedicalstoreadminapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    //for collectAsState
    implementation(libs.androidx.hilt.navigation.compose)

    //navigation
    implementation(libs.androidx.navigation.compose)

    //kotlin serialization
    implementation(libs.kotlinx.serialization.json)

    //retrofit for network calling
    implementation(libs.converter.gson)
    implementation(libs.retrofit2.retrofit)

    //dagger
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    implementation(libs.accompanist.systemuicontroller)
}