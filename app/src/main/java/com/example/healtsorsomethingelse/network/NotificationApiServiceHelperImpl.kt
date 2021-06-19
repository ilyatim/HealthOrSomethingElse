package com.example.healtsorsomethingelse.network

import com.example.healtsorsomethingelse.data.notification.UserNotification
import javax.inject.Inject

class NotificationApiServiceHelperImpl @Inject constructor(
    private val apiService: ProfileApiService
) : NotificationApiServiceHelper {

    override suspend fun getUserNotification(userId: String): List<UserNotification> {
        return apiService.getUserNotification(userId)
    }
}