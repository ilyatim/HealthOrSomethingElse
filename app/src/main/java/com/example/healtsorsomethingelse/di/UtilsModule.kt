package com.example.healtsorsomethingelse.di

import com.example.healtsorsomethingelse.utils.AnimationHelper
import com.example.healtsorsomethingelse.utils.AnimationHelperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ActivityRetainedComponent

@InstallIn(ActivityComponent::class)
@Module
abstract class SingletonModule {
    @Binds
    abstract fun bindAnimationHelper(impl: AnimationHelperImpl): AnimationHelper
}