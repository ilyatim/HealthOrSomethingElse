package com.example.healtsorsomethingelse.di

import com.example.healtsorsomethingelse.utils.AnimationHelper
import com.example.healtsorsomethingelse.utils.AnimationHelperImpl
import com.example.healtsorsomethingelse.utils.notifications.NotificationRepositoryHelper
import com.example.healtsorsomethingelse.utils.notifications.NotificationRepositoryHelperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ActivityComponent::class)
@Module
abstract class SingletonModule {
    @Binds
    abstract fun bindAnimationHelper(impl: AnimationHelperImpl): AnimationHelper
}

@InstallIn(ViewModelComponent::class)
@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindRepositoryHelper(impl: NotificationRepositoryHelperImpl): NotificationRepositoryHelper
}