typealias dep = com.example.dependencies.Dependencies
typealias and = com.example.dependencies.Android

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("dependencies")
}

android {
    compileSdk = and.compileSdk

    defaultConfig {
        minSdk = and.minSdk
        targetSdk = and.targetSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro")
        }
    }
    buildFeatures {
        viewBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    dep.core.apply {
        implementation(androidCoreKtx)
        implementation(appCompat)
    }
    dep.ui.apply {
        implementation(material)
    }
    dep.test.apply {
        testImplementation(junit)
        androidTestImplementation(junitAndroid)
        androidTestImplementation(espressoCore)
    }
}