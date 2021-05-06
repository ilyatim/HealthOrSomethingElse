package com.example.healtsorsomethingelse.extensions

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager


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

    fun View?.hideKeyboard() {
        val imm: InputMethodManager = this?.context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(this.windowToken, 0)
    }
}