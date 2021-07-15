package com.example.healtsorsomethingelse.network

import com.example.healtsorsomethingelse.data.database.Workout
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import javax.inject.Inject

/**
 * Interface that help us handle request on the server to get Workout's
 */
interface WorkoutApiServiceHelper {
    /**
     * Return list of all workout
     * @param number count
     * @return [List]
     */
    suspend fun getAllWorkout(number: Int = WORKOUT_COUNT_BY_DEFAULT): List<Workout>

    /**
     * Return workout by id
     * @param id id of the workout
     * @return [Workout]
     */
    suspend fun getWorkoutById(id: Int): Workout

    /**
     * Return workout by difficult
     * 0 - Beginner
     * 1 - Advanced
     * 2 - Professional
     * @param difficult difficult of the workout
     * @param number count
     * @return [List]
     */
    suspend fun getWorkoutByDifficult(difficult: Int, number: Int = WORKOUT_COUNT_BY_DEFAULT): List<Workout>

    /**
     * Return user saved workout
     * @param id user id
     * @param number count
     * @return [List]
     */
    suspend fun getSavedWorkout(id: String, number: Int = WORKOUT_COUNT_BY_DEFAULT): List<Workout>

    /**
     * Add workout on the server
     * @param workout workout object
     * @return token [String]
     */
    suspend fun addWorkout(workout: Workout): String
}

/**
 * The number of exercises returned from the server
 */
private const val WORKOUT_COUNT_BY_DEFAULT = 20

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