package com.example.healtsorsomethingelse.di

import com.example.healtsorsomethingelse.utils.AnimationHelper
import com.example.healtsorsomethingelse.utils.AnimationHelperImpl
import com.example.healtsorsomethingelse.utils.ErrorHandler
import com.example.healtsorsomethingelse.utils.ErrorHandlerImpl
import com.example.healtsorsomethingelse.utils.notifications.NotificationRepositoryHelper
import com.example.healtsorsomethingelse.utils.notifications.NotificationRepositoryHelperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(ActivityComponent::class)
@Module
abstract class SingletonModule {
    @Binds
    abstract fun bindAnimationHelper(impl: AnimationHelperImpl): AnimationHelper

}

@InstallIn(SingletonComponent::class)
@Module
abstract class SingletonComponents {
    @Binds
    @Singleton
    abstract fun bindErrorHandler(impl: ErrorHandlerImpl): ErrorHandler
}

@InstallIn(ViewModelComponent::class)
@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindRepositoryHelper(impl: NotificationRepositoryHelperImpl): NotificationRepositoryHelper
}