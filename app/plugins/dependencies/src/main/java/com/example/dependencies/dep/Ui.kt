@file:Suppress("unused")

package com.example.dependencies.dep

import com.example.dependencies.Versions

object Ui {
    /**
     * Material Components for Android is a static library that you can add to your
     * Android application in order to use APIs that provide implementations of the Material Design specification.
     * Compatible on devices running API 14 or later.
     * @see [https://mvnrepository.com/artifact/com.google.android.material/material]
     */
    const val material = "com.google.android.material:material:${Versions.materialVersion}"
}

object AndroidXUi {
    /**
     * Android Support RecyclerView
     * @see [https://mvnrepository.com/artifact/androidx.recyclerview/recyclerview]
     */
    const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerViewVersion}"

    /**
     * The Support Library is a static library that you can add to your Android application
     * in order to use APIs that are either not available for older platform versions or utility APIs
     * that aren't a part of the framework APIs. Compatible on devices running API 14 or later.
     * @see [https://mvnrepository.com/artifact/androidx.coordinatorlayout/coordinatorlayout]
     */
    const val coordinatorLayout = "androidx.coordinatorlayout:coordinatorlayout:${Versions.coordinatorLayoutVersion}"

    /**
     * AndroidX Widget ViewPager2
     * @see [https://mvnrepository.com/artifact/androidx.viewpager2/viewpager2]
     */
    const val viewPager2 = "androidx.viewpager2:viewpager2:${Versions.viewPager2Version}"

    /**
     * Android Support CardView v7
     * @see [https://mvnrepository.com/artifact/androidx.cardview/cardview]
     */
    const val cardView = "androidx.cardview:cardview:${Versions.cardViewVersion}"

    /**
     * This library provides the compatibility APIs for SplashScreen and helper method to
     * enable a splashscreen on devices prior Android 12
     * @see [https://mvnrepository.com/artifact/androidx.core/core-splashscreen]
     */
    const val splashScreen = "androidx.core:core-splashscreen:${Versions.splashScreenVersion}"
}