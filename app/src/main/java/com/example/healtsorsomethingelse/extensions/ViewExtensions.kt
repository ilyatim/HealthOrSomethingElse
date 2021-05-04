package com.example.healtsorsomethingelse.extensions

import android.view.View

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
}