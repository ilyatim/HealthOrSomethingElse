package com.example.healtsorsomethingelse.data.home

import com.example.healtsorsomethingelse.data.home.Statistics

/**
 * Interface for HomeFragment that provide data
 */
interface HomeRepository {
    /*
    fun getWeekSteps(): Int
    fun getDaySteps(): Int
    fun getDayCalories(): Int
    fun getDayDistance(): Double
    fun getWeekDistance(): Double
    fun getDayWorkoutCount(): Int
    fun getWeekWorkoutCount(): Int
    */
    /**
     * Return statistics over the day
     * @return [Statistics.DayStatistics]
     */
    suspend fun getDayStatistics(): Statistics.DayStatistics

    /**
     * Return statistics over the week
     * @return [Statistics.WeekStatistics]
     */
    suspend fun getWeekStatistics(): Statistics.WeekStatistics

    /**
     * Fun that return advice
     * @return [String]
     */
    fun getHelpfulAdvice(): String

    /**
     * Fun that return sleep statistics
     * @return [Statistics.SleepStatistics]
     */
    fun getSleepStatistics(): Statistics.SleepStatistics

    /**
     * Return all statistics for home fragment
     * @return [List]
     */
    suspend fun getAllStatistic(): List<Statistics>
}