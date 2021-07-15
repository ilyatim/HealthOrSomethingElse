package com.example.healtsorsomethingelse.network

import com.example.healtsorsomethingelse.data.notification.UserNotification
import javax.inject.Inject

interface NotificationApiServiceHelper {
    /**
     * Return user notifications
     * @param userId user ID
     * @return user notifications: [List]
     */
    suspend fun getUserNotification(userId: String): List<UserNotification>
}

class NotificationApiServiceHelperImpl @Inject constructor(
    private val apiService: ProfileApiService
) : NotificationApiServiceHelper {

    override suspend fun getUserNotification(userId: String): List<UserNotification> {
        return apiService.getUserNotification(userId)
    }
}