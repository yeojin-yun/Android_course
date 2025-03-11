plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt")
}

android {
    namespace = "com.example.buyfuturecloneapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.buyfuturecloneapp"
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
        viewBinding = true
    }
}

dependencies {
    implementation(libs.androidx.material3.android)
    //네비게이션
    val nav_version = "2.7.4"
    implementation("androidx.navigation:navigation-fragment-ktx:$nav_version")
    implementation("androidx.navigation:navigation-ui-ktx:$nav_version")

    // 네비게이션(선택 사항) - 다른 구성요소도 필요하면 추가
    implementation("androidx.navigation:navigation-dynamic-features-fragment:$nav_version")
    implementation("androidx.navigation:navigation-compose:$nav_version") // Jetpack Compose 사용 시

    ///뷰모델
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")

    ///네트워크 관련
    //Network Calls
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    //Json to Kotlin
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    ///이미지 관련
    //image loading
    implementation("io.coil-kt:coil-compose:2.4.0")

    ///위치 관련
    //google play service의 위치 제공 서비스
    implementation("com.google.android.gms:play-services-location:21.0.1")

    //google play services에서 제공하는 전통적인 Google Maps API
    //SupportMapFragment, MapView 등을 사용하여 XML 기반으로 지도를 구현할 때 필요
    implementation("com.google.maps.android:maps-compose:2.15.0")

    //Jetpack Compose에서 Google 지도를 사용할 수 있도록 지원하는 라이브러리
    implementation("com.google.android.gms:play-services-maps:18.1.0")

    //Room
    val room_version = "2.6.1"

    implementation("androidx.room:room-runtime:$room_version")
    implementation("androidx.room:room-ktx:$room_version")
    kapt("androidx.room:room-compiler:$room_version")


    // Core KTX (Jetpack 기본 확장)
    val compose_version = "1.6.0-alpha08"
    implementation("androidx.core:core-ktx:1.12.0")

    // Jetpack Compose
    implementation("androidx.compose.ui:ui:$compose_version")
    implementation("androidx.compose.material:material:$compose_version")
    implementation("androidx.compose.ui:ui-tooling-preview:$compose_version")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}