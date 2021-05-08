package com.example.healtsorsomethingelse.utils

import java.text.SimpleDateFormat
import java.util.*

object TimeUtils {
    fun getCookingTime(time: Long): String {
        val sdf = SimpleDateFormat("hh:mm", Locale.getDefault())
        return sdf.format(Date(time))
    }
}