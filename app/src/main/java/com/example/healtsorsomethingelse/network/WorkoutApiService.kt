package com.example.healtsorsomethingelse.network

import com.example.healtsorsomethingelse.data.database.Workout
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface WorkoutApiService {
    @GET("/workout/all_workout{?limit}")
    suspend fun getAllWorkout(@Path("limit")count: Int): List<Workout>
    @GET("/workout/{workout_id}")
    suspend fun getWorkoutById(@Path("workout_id")id: Int): Workout
    @GET("/workout/{difficult}{?limit}")
    suspend fun getWorkoutByDifficult(@Path("difficult")difficult: Int, @Path("limit")count: Int): List<Workout>
    @GET("/workout/{user_id}/favorite{?limit}")
    suspend fun getSavedWorkout(@Path("user_id")userId: String, @Path("limit")count: Int): List<Workout>
    @POST("/workout/add_workout")
    suspend fun addWorkout(@Body workout: Workout): String
}