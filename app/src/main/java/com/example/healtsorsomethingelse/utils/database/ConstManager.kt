package com.example.healtsorsomethingelse.utils.database

import androidx.lifecycle.Lifecycle
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject
import javax.inject.Singleton

@ActivityScoped
class StateHandler @Inject constructor() {
    private val map: MutableMap<Int, Int> = mutableMapOf()

    fun setViewHolderPosition(globalAdapterPosition: Int, subAdapterPosition: Int) {
        map[globalAdapterPosition] = subAdapterPosition
    }

    fun getViewHolderPosition(globalAdapterPosition: Int): Int? = map[globalAdapterPosition]
}