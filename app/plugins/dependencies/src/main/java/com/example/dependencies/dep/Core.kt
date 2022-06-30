@file:Suppress("unused")

package com.example.dependencies.dep

import com.example.dependencies.Versions

object Core {
    /**
     * Kotlin extensions for 'core' artifact
     * @see [https://mvnrepository.com/artifact/androidx.core/core-ktx]
     */
    const val androidCoreKtx = "androidx.core:core-ktx:${Versions.coreKtxVersion}"

    /**
     * The Support Library is a static library that you can add to your Android application in order
     * to use APIs that are either not available for older platform versions or utility APIs
     * that aren't a part of the framework APIs. Compatible on devices running API 14 or later.
     * @see [https://mvnrepository.com/artifact/androidx.appcompat/appcompat]
     */
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompatVersion}"

    /**
     * Kotlin extensions for preferences
     * @see [https://mvnrepository.com/artifact/androidx.preference/preference-ktx]
     */
    const val preference = "androidx.preference:preference-ktx:${Versions.preferencesVersion}"

    /**
     * The Support Library is a static library that you can add to your Android application in order
     * to use APIs that are either not available for older platform versions or utility APIs that
     * aren't a part of the framework APIs. Compatible on devices running API 14 or later.
     * @see [https://mvnrepository.com/artifact/androidx.fragment/fragment]
     */
    const val fragment = "androidx.fragment:fragment:${Versions.fragmentVersion}"

    /**
     * Kotlin extensions for 'fragment' artifact
     * @see [https://mvnrepository.com/artifact/androidx.fragment/fragment-ktx]
     */
    const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragmentVersion}"

    /**
     * Android Navigation UI Kotlin Extensions
     * @see [https://mvnrepository.com/artifact/androidx.navigation/navigation-ui-ktx]
     */
    const val navigationUiKtx = "androidx.navigation:navigation-ui-ktx:${Versions.navigationVersion}"

    /**
     * Android Navigation Fragment Kotlin Extensions
     * @see [https://mvnrepository.com/artifact/androidx.navigation/navigation-fragment-ktx]
     */
    const val navigationFragmentKtx = "androidx.navigation:navigation-fragment-ktx:${Versions.navigationVersion}"
}