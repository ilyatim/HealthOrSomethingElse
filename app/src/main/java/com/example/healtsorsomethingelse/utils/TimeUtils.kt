package com.example.healtsorsomethingelse.utils

import java.sql.Time
import java.text.SimpleDateFormat
import java.util.*

object TimeUtils {

    const val TIME_IN_DAY: Long = 60 * 60 * 24 * 1000
    private val COOKING_TIME_DATE_PATTERN: String = "HH:mm"
    private val NOTIFICATIONS_TIME_DATE_PATTERN: String = "d MMMM HH:mm"

    fun getCookingTime(time: Long): String {
        return SimpleDateFormat(COOKING_TIME_DATE_PATTERN, Locale.getDefault()).apply {
            timeZone = TimeZone.getTimeZone("GMT")
        }.format(time)
    }

    fun parseNotificationDate(time: Long): String {
        return SimpleDateFormat(NOTIFICATIONS_TIME_DATE_PATTERN, Locale.getDefault()).format(time)
    }

    fun getDayCountFromDate(time: Long): Int {
        val value = time / TIME_IN_DAY
        return (time / TIME_IN_DAY).toInt()
    }
}