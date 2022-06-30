@file:Suppress("unused")

package com.example.dependencies.dep

import com.example.dependencies.Versions

object Lifecycle {
    /**
     * Kotlin extensions for 'livedata' artifact
     * @see [https://mvnrepository.com/artifact/androidx.lifecycle/lifecycle-livedata-ktx]
     */
    const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.liveDataVersion}"

    /**
     * Kotlin extensions for 'viewmodel' artifact
     * @see [https://mvnrepository.com/artifact/androidx.lifecycle/lifecycle-viewmodel-ktx]
     */
    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.viewModelVersion}"
}