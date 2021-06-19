package com.example.healtsorsomethingelse.network

import com.example.healtsorsomethingelse.data.notification.UserNotification

interface NotificationApiServiceHelper {
    /**
     * Return user notifications
     * @param userId user ID
     * @return user notifications: [List]
     */
    suspend fun getUserNotification(userId: String): List<UserNotification>
}