package com.example.healtsorsomethingelse.data.home

/**
 * Class that store data to home adapter
 */
sealed class Statistics {
    /**
     * Class that contain info about sleep
     */
    data class SleepStatistics(
        val sleepTime: Int,
        val averageSleepTime: Int,
        val recommendedSleepTime: Int
        ) : Statistics()

    /**
     * Class that contain info about day values
     */
    data class DayStatistics(val items: List<DayStatisticsItem>) : Statistics()

    /**
     * Class that contain info about week values
     */
    data class WeekStatistics(val items: List<WeekStatisticsItem>) : Statistics()

    /**
     * Class that contain helpful advice
     */
    data class Advice(val advice: String) : Statistics()
}

/**
 * Wrapper over day values
 */
sealed class DayStatisticsItem {
    /**
     * Calories info
     */
    data class Calories(val number: Int, val ratio: Int) : DayStatisticsItem()

    /**
     * Steps info
     */
    data class Steps(val number: Int, val ratio: Int) : DayStatisticsItem()

    /**
     * Traveled distance
     */
    data class Distance(val distance: Double, val ratio: Int) : DayStatisticsItem()

    /**
     * Number of workout today
     */
    data class Workout(val number: Int) : DayStatisticsItem()
}

/**
 * Statistics over the week
 */
sealed class WeekStatisticsItem {
    //data class Calories(val inTotal: Int, val average: Int) : WeekStatistics()

    /**
     * Steps count over the week
     */
    data class Steps(val inTotal: Int, val average: Int, val ratio: Int) : WeekStatisticsItem()

    /**
     * Traveled distance over the week
     */
    data class Distance(val inTotal: Double, val average: Double, val ratio: Int) : WeekStatisticsItem()

    /**
     * Number of workout over the week
     */
    data class Workout(val inTotal: Int, val quantityInRelationToPast: Int) : WeekStatisticsItem()
}