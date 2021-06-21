package com.example.healtsorsomethingelse.data.notification

sealed class Notifications

data class UserNotification(
    val id: String,
    val imageUrl: String,
    val userName: String,
    val date: Long,
    val topic: String
) : Notifications()

data class NotificationTopic(
    val text: String
) : Notifications()


enum class NotificationDate {
    TODAY,
    YESTERDAY,
    LAST_WEEK,
    LAST_MONTH,
    BEFORE,
}