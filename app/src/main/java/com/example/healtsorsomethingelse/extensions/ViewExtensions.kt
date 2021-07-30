package com.example.healtsorsomethingelse.extensions

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow


object ViewExtensions {

    fun View?.gone() {
        this?.visibility = View.GONE
    }

    fun View?.visible() {
        this?.visibility = View.VISIBLE
    }

    fun View?.invisible() {
        this?.visibility = View.INVISIBLE
    }

    @ExperimentalCoroutinesApi
    fun View.click(): Flow<Unit> = callbackFlow {
        setOnClickListener {
            offer(Unit)
        }
        awaitClose { setOnClickListener(null) }
    }

    fun View?.hideKeyboard() {
        val imm: InputMethodManager = this?.context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(this.windowToken, 0)
    }

    val View.isKeyboardVisible: Boolean
        get() = ViewCompat
            .getRootWindowInsets(this)
            ?.isVisible(WindowInsetsCompat.Type.ime()) == true
}