plugins {
    id("com.android.application")
    id("kotlin-android")
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
}

android {
    compileSdk = 31
    buildToolsVersion = "30.0.3"

    defaultConfig {
        applicationId = "com.example.healtsorsomethingelse"
        minSdk = 24
        targetSdk = 31
        versionCode = 1
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

    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    //Lifecycle
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.4.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.0")

    implementation("androidx.preference:preference-ktx:1.1.1")
    val navVersion = "2.3.5"
    val hiltVersion = "2.40.1"
    val kotlinVersion = "1.6.0"
    val gradleVersion = "7.2.0"
    //implementation fileTree(dir: "libs", include: ["*.jar"])
    //noinspection GradleDependency
    implementation("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion")
    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.appcompat:appcompat:1.4.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.1")
    implementation("com.google.android.gms:play-services-auth:19.2.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")

    //Splash screen
    implementation("androidx.core:core-splashscreen:1.0.0-alpha02")
    //Fragments
    implementation("androidx.fragment:fragment-ktx:1.4.0")
    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.retrofit2:converter-scalars:2.1.0")
    //LiveData
    implementation("android.arch.lifecycle:livedata:1.1.1")
    //Recycler view
    implementation("androidx.recyclerview:recyclerview:1.2.1")
    //ViewModel
    implementation("android.arch.lifecycle:viewmodel:1.1.1")
    //Glide
    implementation("com.github.bumptech.glide:glide:4.12.0")
    //Hilt
    implementation("com.google.dagger:hilt-android:$hiltVersion")
    kapt("com.google.dagger:hilt-android-compiler:$hiltVersion")

    // For Robolectric tests.
    testImplementation("com.google.dagger:hilt-android-testing:$hiltVersion")
    // ...with Kotlin.
    kaptTest("com.google.dagger:hilt-android-compiler:$hiltVersion")

    // Hilt testing dependencies
    androidTestImplementation("com.google.dagger:hilt-android-testing:$hiltVersion")
    kaptAndroidTest("com.google.dagger:hilt-android-compiler:$hiltVersion")

    //Navigation
    implementation("androidx.navigation:navigation-ui-ktx:$navVersion")
    implementation("androidx.navigation:navigation-fragment-ktx:$navVersion")

    //Card view
    implementation("androidx.cardview:cardview:1.0.0")

    //Maretial
    implementation("androidx.fragment:fragment-ktx:1.4.0")
    implementation("androidx.coordinatorlayout:coordinatorlayout:1.1.0")
    implementation("com.google.android.material:material:1.5.0-beta01")

    //ViewPager2
    implementation("androidx.viewpager2:viewpager2:1.1.0-beta01")

    //LeakCanary debug dependency
    debugImplementation("com.squareup.leakcanary:leakcanary-android:2.7")
}