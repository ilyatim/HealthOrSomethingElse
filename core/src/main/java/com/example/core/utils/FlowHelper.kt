package com.example.core.utils

import android.content.Context
import kotlinx.coroutines.MainScope

fun Context.showViewWithPost(
    delay: Long = 500L,
    destination: () -> Unit
) {
    /*throttleFirst(
        delay,
        coroutineScope = MainScope(),
        destination
    )*/
}