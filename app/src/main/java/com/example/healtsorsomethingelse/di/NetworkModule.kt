package com.example.healtsorsomethingelse.di

import com.example.healtsorsomethingelse.network.*
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideBaseUrl(): String = "https://private-2ff2f-healthorsomethingelse.apiary-mock.com"

    @Provides
    fun provideGson(): Gson = GsonBuilder().setLenient().create()

    @Singleton
    @Provides
    fun provideRetrofit(BASE_URL:String): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(provideGson()))
        .addConverterFactory(ScalarsConverterFactory.create())
        .client(OkHttpClient
            .Builder()
            .build())
        .baseUrl(BASE_URL)
        .build()

    @Provides
    @Singleton
    fun provideRecipesApiService(retrofit: Retrofit): RecipesApiService {
        return retrofit.create(RecipesApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRecipesNetworkServiceHelper(impl: RecipesApiServiceHelperImpl): RecipesApiServiceHelper {
        return impl
    }

    @Provides
    @Singleton
    fun provideProfileApiService(retrofit: Retrofit): ProfileApiService {
        return retrofit.create(ProfileApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideProfileNetworkServiceHelper(impl: ProfileApiServiceHelperImpl): ProfileApiServiceHelper {
        return impl
    }

    @Provides
    @Singleton
    fun provideWorkoutNetworkServiceHelper(impl: WorkoutApiServiceHelperImpl): WorkoutApiServiceHelper {
        return impl
    }

    @Provides
    @Singleton
    fun provideWorkoutApiService(retrofit: Retrofit): WorkoutApiService {
        return retrofit.create(WorkoutApiService::class.java)
    }

}