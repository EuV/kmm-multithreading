plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-android-extensions")
}

dependencies {
    implementation(project(":shared"))
    implementation("com.google.android.material:material:1.3.0")
    implementation("androidx.appcompat:appcompat:1.2.0")
}

android {
    compileSdkVersion(29)
    defaultConfig {
        applicationId = "kmm.multithreading.androidApp"
        minSdkVersion(24)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}
