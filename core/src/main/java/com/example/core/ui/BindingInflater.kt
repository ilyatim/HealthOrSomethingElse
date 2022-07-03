package com.example.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup

interface ViewInflater<T> {
    fun invoke(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachToParent: Boolean
    ): T
}

interface ActivityInflater<T> : ViewInflater<T> {
    fun invoke(
        layoutInflater: LayoutInflater,
    ): T
}