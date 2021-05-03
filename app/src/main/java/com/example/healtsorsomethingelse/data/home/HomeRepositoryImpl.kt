package com.example.healtsorsomethingelse.data.home

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    /*@ActivityContext private val context: Context*/
) : HomeRepository {

   /* @Inject lateinit */var googleSignInAccount: GoogleSignInAccount? = null/*GoogleSignIn.getLastSignedInAccount(context)*/

    override suspend fun getDayStatistics(): Statistics.DayStatistics {
        return Statistics.DayStatistics(listOf(
            DayStatisticsItem.Steps(120, 10),
        ))
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

    override suspend fun getAllStatistic(): List<Statistics> {
        return listOf(
            getDayStatistics(),
            getWeekStatistics(),
            getSleepStatistics(),
            getSleepStatistics(),
            getSleepStatistics(),
            getSleepStatistics(),
            Statistics.Advice(getHelpfulAdvice()),
            Statistics.Advice(getHelpfulAdvice()),
            Statistics.Advice(getHelpfulAdvice()),
            Statistics.Advice(getHelpfulAdvice()),
        )
    }
}