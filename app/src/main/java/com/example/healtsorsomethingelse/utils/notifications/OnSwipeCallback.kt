package com.example.healtsorsomethingelse.utils.notifications

fun interface OnSwipeCallback {
    fun onSwipe(position: Int, notId: String)
}