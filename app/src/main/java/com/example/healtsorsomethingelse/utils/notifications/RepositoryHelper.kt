package com.example.healtsorsomethingelse.utils.notifications

import android.app.Notification
import android.content.Context
import android.text.format.Time
import android.util.Log
import com.example.healtsorsomethingelse.R
import com.example.healtsorsomethingelse.data.notification.NotificationTopic
import com.example.healtsorsomethingelse.data.notification.Notifications
import com.example.healtsorsomethingelse.data.notification.UserNotification
import com.example.healtsorsomethingelse.utils.TimeUtils
import dagger.hilt.android.qualifiers.ApplicationContext
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

interface NotificationRepositoryHelper {
    fun addDateToNotification(list: MutableList<Notifications>)
}

class NotificationRepositoryHelperImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : NotificationRepositoryHelper {

    override fun addDateToNotification(list: MutableList<Notifications>) {
        if (list.isEmpty()) return
        val dateMap: MutableMap<Int, NotificationTopic> = mutableMapOf()
        list.forEachIndexed { index, notification ->
            if (index == 0 && notification is UserNotification) {
                dateMap[index] = NotificationTopic(getDateText(notification.date))
            } else if (notification is UserNotification){
                val previous = list[index - 1] as UserNotification
                if (TimeUtils.getDayCountFromDate(previous.date - notification.date) > 0) {
                    dateMap[index + dateMap.size] = NotificationTopic(getDateText(notification.date))
                }
            }
        }
        dateMap.forEach {
            list.add(it.key, it.value)
        }
    }

    private fun getDateText(date: Long): String {
        val diff = System.currentTimeMillis() - date
        return when (TimeUtils.getDayCountFromDate(diff)) {
            0 -> {
                if (Date(System.currentTimeMillis()).date - Date(date).date == 0) {
                    context.getString(R.string.today)
                } else {
                    context.getString(R.string.yesterday)
                }
            }
            1 -> context.getString(R.string.yesterday)
            in 2..7 -> context.getString(R.string.last_week)
            else -> context.getString(R.string.last_month)
        }
    }
}