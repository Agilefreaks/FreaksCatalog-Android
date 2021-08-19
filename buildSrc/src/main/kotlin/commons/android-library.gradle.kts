package commons

import extensions.addTestDependencies
import dependencies.Dependencies

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs")
}

android {
    compileSdk = BuildAndroidConfig.COMPILE_SDK_VERSION

    defaultConfig {
        minSdk = BuildAndroidConfig.MIN_SDK_VERSION
        targetSdk = BuildAndroidConfig.TARGET_SDK_VERSION
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    buildFeatures {
        dataBinding = true
        viewBinding = true
    }

    sourceSets {
        getByName("main") {
            java.srcDir("src/main/kotlin")
        }
        getByName("test") {
            java.srcDir("src/test/kotlin")
        }
        getByName("androidTest") {
            java.srcDir("src/androidTest/kotlin")
        }
    }

    lint {
        lintConfig = rootProject.file(".lint/config.xml")
        isCheckAllWarnings = true
        isWarningsAsErrors = true
    }

    testOptions {
        unitTests.isIncludeAndroidResources = true
        unitTests.isReturnDefaultValues = true
    }
}

dependencies {
    implementation(Dependencies.Kotlin.STDLIB)
    implementation(Dependencies.Coroutines.CORE)
    implementation(Dependencies.Coroutines.ANDROID)

    implementation(Dependencies.AndroidX.CORE)
    implementation(Dependencies.AndroidX.APPCOMPAT)
    implementation(Dependencies.AndroidX.CONSTRAINTLAYOUT)

    implementation(Dependencies.LOTTIE)

    implementation(Dependencies.COIL)

    implementation(Dependencies.AndroidX.Navigation.FRAGMENT)
    implementation(Dependencies.AndroidX.Navigation.UI)
    implementation(Dependencies.AndroidX.Navigation.DYNAMIC_FEATURE)

    implementation(Dependencies.Apollo.COROUTINES_SUPPORT)
    implementation(Dependencies.Apollo.RUNTIME)

    implementation(Dependencies.Picasso.PICASSO){
        exclude(Dependencies.Picasso.PICASSO_ANDROID_SUPPORT)
        exclude(Dependencies.Picasso.PICASSO_EXIFINTERACE)
    }

    implementation(Dependencies.Koin.CORE)
    implementation(Dependencies.Koin.ANDROID)
    implementation(Dependencies.Koin.ANDROID_VIEWMODEL)

    addTestDependencies()
}
