package com.example.healtsorsomethingelse.utils

import java.text.SimpleDateFormat
import java.util.*

object TimeUtils {

    private val COOKING_TIME_DATE_PATTERN: String = "HH:mm"
    private val NOTIFICATIONS_TIME_DATE_PATTERN: String = "d MMMM HH:mm"

    fun getCookingTime(time: Long): String {
        val sdf = SimpleDateFormat(COOKING_TIME_DATE_PATTERN, Locale.getDefault())
        return sdf.format(Date(time))
    }

    fun parseNotificationDate(time: Long): String {
        val sdf = SimpleDateFormat(NOTIFICATIONS_TIME_DATE_PATTERN, Locale.getDefault())
        return sdf.format(Date(time))
    }
}