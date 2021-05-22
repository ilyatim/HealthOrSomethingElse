package com.example.healtsorsomethingelse.extensions

import android.content.Context
import android.widget.Toast

object ContextExtensions {
    fun Context?.showLongToast(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}