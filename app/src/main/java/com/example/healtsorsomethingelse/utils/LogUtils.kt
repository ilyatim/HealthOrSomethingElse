package com.example.healtsorsomethingelse.utils

import android.util.Log

object LogUtils {
    fun log(message: String, priority: Int, tag: String) {
        when (priority) {
            Log.DEBUG -> {
                Log.d(tag, message)
            }
            Log.ERROR -> {
                Log.e(tag, message)
            }
            Log.WARN -> {
                Log.w(tag, message)
            }
            Log.VERBOSE -> {
                Log.v(tag, message)
            }
            Log.INFO -> {
                Log.i(tag, message)
            }
        }
    }
}