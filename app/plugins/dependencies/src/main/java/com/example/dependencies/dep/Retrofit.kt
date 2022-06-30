@file:Suppress("unused")

package com.example.dependencies.dep

import com.example.dependencies.Versions

object Retrofit {
    /**
     * A type-safe HTTP client for Android and Java.
     * @see [https://mvnrepository.com/artifact/com.squareup.retrofit2/retrofit]
     */
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"

    /**
     * A Retrofit Converter which uses Gson for serialization.
     * @see [https://mvnrepository.com/artifact/com.squareup.retrofit2/converter-gson]
     */
    const val retrofitConverterGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofitVersion}"

    /**
     * A Retrofit Converter for Java's scalar value types.
     * @see [https://mvnrepository.com/artifact/com.squareup.retrofit2/converter-scalars]
     */
    const val retrofitConverterScalars = "com.squareup.retrofit2:converter-scalars:${Versions.retrofitVersion}"
}