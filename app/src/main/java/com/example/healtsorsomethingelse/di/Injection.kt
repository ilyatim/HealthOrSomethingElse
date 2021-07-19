package com.example.healtsorsomethingelse.di

import com.example.healtsorsomethingelse.utils.database.StateHandler
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent


@EntryPoint
@InstallIn(ActivityComponent::class)
interface ConstManagerInterface {
    fun getStateHandler(): StateHandler
}