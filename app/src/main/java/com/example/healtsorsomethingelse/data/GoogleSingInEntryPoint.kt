package com.example.healtsorsomethingelse.data

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import dagger.Binds
import dagger.Module
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/*
@EntryPoint
@InstallIn(SingletonComponent::class)
interface GoogleSingInEntryPoint {
    fun getGoogleSingIn(): GoogleSignInAccount
}

@Module
@InstallIn(SingletonComponent::class)
abstract class GoogleSingInModule {
    @Binds
    @Singleton
    abstract fun bindGoogleSingIn(@ApplicationContext context: Context): GoogleSignInAccount?
}*/
