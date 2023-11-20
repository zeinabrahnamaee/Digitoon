plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
//    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.divartask"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.divartask"
        minSdk = 24
        targetSdk = 33
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        dataBinding = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.8.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:4.10.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

//    //Coroutine Li(ecycle Scope
//    implementation("org.jetbrains.Kotlinx:Kotlinx-Coroutines-android:1.7.1")
//    implementation("org.jetbrains.Kotlinx:Kotlinx-Coroutines-core:1.7.1")
//    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")

    //sdp-ssp
//    implementation('com.intuit.sdp:sdp-android:1.1.0')
//    implementation('com.intuit.ssp:ssp-android:1.1.0')

    implementation ("io.insert-koin:koin-android:3.4.3")
    implementation ("io.insert-koin:koin-core:3.4.3")

    //ok-http
    //noinspection BomWithoutPlatform
    implementation("com.squareup.okhttp3:okhttp-bom:4.10.0")

    //glide
    implementation("com.github.bumptech.glide:glide:4.14.2")

    //room
    implementation("androidx.room:room-runtime:2.6.0-rc01")
    implementation("androidx.room:room-paging:2.6.0-rc01")
    implementation("androidx.room:room-ktx:2.6.0-rc01")
//    kapt("androidx.room:room-compiler:2.6.0-rc01")

    //nav
    implementation("androidx.navigation:navigation-fragment-ktx:2.4.2")
    implementation("androidx.navigation:navigation-ui-ktx:2.4.2")


    //HILT
    implementation ("com.google.dagger:hilt-android:2.48.1")
//    org.jetbrains.kotlin.kapt3.base.Kapt.kapt("com.google.dagger:hilt-compiler:2.48.1")


}