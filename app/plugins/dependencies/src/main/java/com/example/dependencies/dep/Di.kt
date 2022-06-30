@file:Suppress("unused")

package com.example.dependencies.dep

import com.example.dependencies.Versions

object Di {
    /**
     * A fast dependency injector for Android and Java.
     * @see [https://mvnrepository.com/artifact/com.google.dagger/hilt-android]
     */
    const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.hiltVersion}"

    /**
     * A fast dependency injector for Android and Java.
     * @see [https://mvnrepository.com/artifact/com.google.dagger/hilt-android-compiler]
     */
    const val daggerHiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hiltVersion}"
}