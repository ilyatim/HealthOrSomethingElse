package com.example.healtsorsomethingelse.di

import android.content.Context
import androidx.core.app.ActivityCompat
import com.example.healtsorsomethingelse.data.database.DialogRepository
import com.example.healtsorsomethingelse.data.database.DialogRepositoryImpl
import com.example.healtsorsomethingelse.data.database.FoodRepository
import com.example.healtsorsomethingelse.data.database.FoodRepositoryImpl
import com.example.healtsorsomethingelse.data.home.HomeRepository
import com.example.healtsorsomethingelse.data.home.HomeRepositoryImpl
import com.example.healtsorsomethingelse.data.notification.NotificationRepository
import com.example.healtsorsomethingelse.data.notification.NotificationRepositoryImpl
import com.example.healtsorsomethingelse.data.profile.ProfileRepository
import com.example.healtsorsomethingelse.data.profile.ProfileRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    @ViewModelScoped
    abstract fun bindHomeRepository(impl: HomeRepositoryImpl): HomeRepository

    @Binds
    @ViewModelScoped
    abstract fun bindProfileRepository(impl: ProfileRepositoryImpl): ProfileRepository

    @Binds
    @ViewModelScoped
    abstract fun bindDialogRepository(impl: DialogRepositoryImpl): DialogRepository

    @Binds
    @ViewModelScoped
    abstract fun bindNotificationRepository(impl: NotificationRepositoryImpl): NotificationRepository
}

@Module
@InstallIn(SingletonComponent::class)
abstract class FoodRepositoryModule {
    @Binds
    @Singleton
    abstract fun bindFoodRepository(impl: FoodRepositoryImpl): FoodRepository
}

