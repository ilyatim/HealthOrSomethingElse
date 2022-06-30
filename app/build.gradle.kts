typealias and = com.example.dependencies.Android
typealias dep = com.example.dependencies.Dependencies

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
    id("dependencies")
}

android {
    compileSdk = and.compileSdk
    buildToolsVersion = and.buildTools

    defaultConfig {
        applicationId = "com.example.healtsorsomethingelse"
        minSdk = and.minSdk
        targetSdk = and.targetSdk
        versionCode = and.versionCode
        versionName = "1.0"

        testInstrumentationRunner = "com.example.healtsorsomethingelse.TestRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        //compose = true
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    //fileTree(dir: "libs", include: ["*.jar"])
    dep.hilt.apply {
        implementation(hiltAndroid)
        kapt(daggerHiltAndroidCompiler)
        kaptTest(daggerHiltAndroidCompiler)
        kaptAndroidTest(daggerHiltAndroidCompiler)
    }
    dep.retrofit.apply {
        implementation(retrofit)
        implementation(retrofitConverterGson)
        implementation(retrofitConverterScalars)
    }
    dep.test.apply {
        testImplementation(junit)
        androidTestImplementation(junitAndroid)
        androidTestImplementation(espressoCore)
        testImplementation(hiltAndroidTesting)
    }
    dep.androidXUi.apply {
        implementation(recyclerView)
        implementation(coordinatorLayout)
        implementation(viewPager2)
        implementation(splashScreen)
        implementation(cardView)
    }
    dep.ui.apply {
        implementation(material)
    }
    dep.lifecycle.apply {
        implementation(liveData)
        implementation(viewModel)
    }
    dep.other.apply {
        implementation(glide)
        implementation(playServiceAuth)
    }
    dep.core.apply {
        implementation(androidCoreKtx)
        implementation(appCompat)
        implementation(preference)
        implementation(fragment)
        implementation(fragmentKtx)
        implementation(navigationUiKtx)
        implementation(navigationFragmentKtx)
    }
}