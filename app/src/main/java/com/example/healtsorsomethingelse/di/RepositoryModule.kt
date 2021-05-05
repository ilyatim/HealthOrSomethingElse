package com.example.healtsorsomethingelse.di

import android.content.Context
import androidx.core.app.ActivityCompat
import com.example.healtsorsomethingelse.data.home.HomeRepository
import com.example.healtsorsomethingelse.data.home.HomeRepositoryImpl
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

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    @ViewModelScoped
    abstract fun bindRepository(impl: HomeRepositoryImpl): HomeRepository

    @Binds
    @ViewModelScoped
    abstract fun bindRepository(impl: ProfileRepositoryImpl): ProfileRepository
}

