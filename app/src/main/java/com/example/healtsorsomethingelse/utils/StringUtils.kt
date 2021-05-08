package com.example.healtsorsomethingelse.utils

import android.content.Context
import com.example.healtsorsomethingelse.R

object StringUtils {

    val ERROR_UNKNOWN = "unknown"

    fun getErrorText(message: String, context: Context): String {
        return when (message) {
            ERROR_UNKNOWN -> context.getString(R.string.unknown)
            else -> context.getString(R.string.unknown)
        }
    }
}