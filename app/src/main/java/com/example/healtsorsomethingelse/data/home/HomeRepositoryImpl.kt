package com.example.healtsorsomethingelse.data.home

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject


class HomeRepositoryImpl @Inject constructor(
    @ActivityContext private val context: Context
) : HomeRepository {

    @Inject private lateinit var googleSignInAccount: GoogleSignInAccount

    override suspend fun getDayStatistics(): Statistics.DayStatistics {
        return Statistics.DayStatistics(listOf(DayStatisticsItem.Steps(120, 10)))
    }

    override suspend fun getWeekStatistics(): Statistics.WeekStatistics {
        return Statistics.WeekStatistics(listOf(WeekStatisticsItem.Workout(5, 4)))
    }

    override fun getHelpfulAdvice(): String {
        return "helpful advice"
    }

    override fun getSleepStatistics(): Statistics.SleepStatistics {
        return Statistics.SleepStatistics(120, 120, 120)
    }
}