package com.example.dependencies.dep

import com.example.dependencies.Versions

object Test {
    const val junit = "junit:junit:4.13.2"
    const val junitAndroid = "androidx.test.ext:junit:1.1.3"
    const val espressoCore = "androidx.test.espresso:espresso-core:3.4.0"

    const val hiltAndroidTesting = "com.google.dagger:hilt-android-testing:${Versions.hiltVersion}"
    const val hiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hiltVersion}"
}