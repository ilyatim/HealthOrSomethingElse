package com.example.healtsorsomethingelse.utils

import android.content.Context
import com.example.healtsorsomethingelse.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

interface ErrorHandler {
    fun getUnknownErrorMessage(): String
    fun getErrorMessage(message: String?): String
}

@Singleton
class ErrorHandlerImpl @Inject constructor(@ApplicationContext val context: Context) : ErrorHandler {
    override fun getUnknownErrorMessage(): String {
        return context.getString(R.string.unknown)
    }

    override fun getErrorMessage(message: String?): String {
        return getUnknownErrorMessage()
    }
}