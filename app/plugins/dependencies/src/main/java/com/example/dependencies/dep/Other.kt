@file:Suppress("unused")

package com.example.dependencies.dep

import com.example.dependencies.Versions

object Other {
    /**
     * A fast and efficient image loading library for Android focused on smooth scrolling.
     * @see [https://mvnrepository.com/artifact/com.github.bumptech.glide/glide]
     */
    const val glide = "com.github.bumptech.glide:glide:${Versions.glideVersion}"

    /**
     * Play Services Auth
     * @see [https://mvnrepository.com/artifact/com.google.android.gms/play-services-auth]
     */
    const val playServiceAuth = "com.google.android.gms:play-services-auth:${Versions.playVersion}"
}