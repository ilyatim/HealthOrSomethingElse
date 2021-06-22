package com.example.healtsorsomethingelse.data.home

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject
import kotlin.random.Random

class HomeRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    val googleSignInAccount: GoogleSignInAccount?
) : HomeRepository {

    override suspend fun getDayStatistics(): Statistics.DayStatistics {
        return Statistics.DayStatistics(listOf(
            DayStatisticsItem.Steps(120, 10),
            DayStatisticsItem.Calories(1200, -40),
            DayStatisticsItem.Distance(1.29, 20),
            DayStatisticsItem.Workout(1)
        ))
    }

    override suspend fun getWeekStatistics(): Statistics.WeekStatistics {
        return Statistics.WeekStatistics(listOf(
            WeekStatisticsItem.Workout(5, 4),
            WeekStatisticsItem.Steps(21000, 7000, 20),
            WeekStatisticsItem.Distance(25.4, 3.6, 20),
        ))
    }

    override fun getHelpfulAdvice(): String {
        return "Чистить зубы перед сном очень полезно"
    }

    override fun getSleepStatistics(): Statistics.SleepStatistics {
        return Statistics.SleepStatistics(28800000, 28800000, 28800000)
    }

    override suspend fun getAllStatistic(): List<Statistics> {
        return listOf(
            getDayStatistics(),
            getWeekStatistics(),
            getSleepStatistics(),
            Statistics.Advice(getHelpfulAdvice()),
        )
    }

    override suspend fun checkNotificationAvailability(): Boolean {
        return Random.nextBoolean()
    }
}