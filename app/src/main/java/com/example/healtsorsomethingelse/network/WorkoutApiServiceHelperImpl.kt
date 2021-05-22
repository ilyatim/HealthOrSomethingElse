package com.example.healtsorsomethingelse.network

import com.example.healtsorsomethingelse.data.database.Workout
import javax.inject.Inject

class WorkoutApiServiceHelperImpl @Inject constructor(
    private val apiService: WorkoutApiService
): WorkoutApiServiceHelper {

    override suspend fun getAllWorkout(number: Int): List<Workout> {
        return apiService.getAllWorkout(number)
    }

    override suspend fun getWorkoutById(id: Int): Workout {
        return apiService.getWorkoutById(id)
    }

    override suspend fun getWorkoutByDifficult(difficult: Int, number: Int): List<Workout> {
        return apiService.getWorkoutByDifficult(difficult, number)
    }

    override suspend fun getSavedWorkout(id: String, number: Int): List<Workout> {
        return apiService.getSavedWorkout(id, number)
    }

    override suspend fun addWorkout(workout: Workout): String {
        return apiService.addWorkout(workout)
    }
}