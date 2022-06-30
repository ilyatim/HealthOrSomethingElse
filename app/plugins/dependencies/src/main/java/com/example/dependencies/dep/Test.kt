@file:Suppress("unused")

package com.example.dependencies.dep

import com.example.dependencies.Versions

object Test {
    /**
     * JUnit is a unit testing framework for Java, created by Erich Gamma and Kent Beck.
     * @see [https://mvnrepository.com/artifact/junit/junit]
     */
    const val junit = "junit:junit:${Versions.junitVersion}"

    /**
     * JUnit extensions for android
     * @see [https://mvnrepository.com/artifact/androidx.test.ext/junit]
     */
    const val junitAndroid = "androidx.test.ext:junit:${Versions.junitAndroidVersion}"

    /**
     * The AndroidX Test Library provides an extensive framework for testing Android apps
     * @see [https://mvnrepository.com/artifact/androidx.test.espresso/espresso-core]
     */
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoVersion}"

    /**
     * A fast dependency injector for Android and Java.
     * @see [https://mvnrepository.com/artifact/com.google.dagger/hilt-android-testing]
     */
    const val hiltAndroidTesting = "com.google.dagger:hilt-android-testing:${Versions.hiltVersion}"

}