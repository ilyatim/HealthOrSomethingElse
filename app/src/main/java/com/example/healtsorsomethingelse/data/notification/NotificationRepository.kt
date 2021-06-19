package com.example.healtsorsomethingelse.data.notification

import android.content.Context
import com.example.healtsorsomethingelse.R
import com.example.healtsorsomethingelse.network.NotificationApiServiceHelper
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

interface NotificationRepository {
    suspend fun getUserNotification(): List<UserNotification>
    fun getErrorMessage(error: String?): String
}

class NotificationRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val googleSingIn: GoogleSignInAccount?,
    private val networkServiceHelper: NotificationApiServiceHelper
) : NotificationRepository {

    override suspend fun getUserNotification(): List<UserNotification> {
        return listOf(
            UserNotification("https://sun9-12.userapi.com/impf/c851128/v851128814/16940d/2zddRMW33fM.jpg?size=839x777&quality=96&sign=e5acb9d395c2e44ac496ecad5aea8695&type=album", "Ясосу Бибу", System.currentTimeMillis(), "Да да, я - сосу Бибу"),
            UserNotification("https://sun9-12.userapi.com/impf/c851128/v851128814/16940d/2zddRMW33fM.jpg?size=839x777&quality=96&sign=e5acb9d395c2e44ac496ecad5aea8695&type=album", "Ясосу Бибу", System.currentTimeMillis(), "Да да, я - сосу Бибу"),
            UserNotification("https://sun9-12.userapi.com/impf/c851128/v851128814/16940d/2zddRMW33fM.jpg?size=839x777&quality=96&sign=e5acb9d395c2e44ac496ecad5aea8695&type=album", "Ясосу Бибу", System.currentTimeMillis(), "Да да, я - сосу Бибу"),
            UserNotification("https://sun9-12.userapi.com/impf/c851128/v851128814/16940d/2zddRMW33fM.jpg?size=839x777&quality=96&sign=e5acb9d395c2e44ac496ecad5aea8695&type=album", "Ясосу Бибу", System.currentTimeMillis() - 60 * 60 * 24 * 1000, "Да да, я - сосу Бибу"),
            UserNotification("https://sun9-12.userapi.com/impf/c851128/v851128814/16940d/2zddRMW33fM.jpg?size=839x777&quality=96&sign=e5acb9d395c2e44ac496ecad5aea8695&type=album", "Ясосу Бибу", System.currentTimeMillis() - 6 * 60 * 60 * 24 * 1000, "Да да, я - сосу Бибу")
        )
        //return networkServiceHelper.getUserNotification(googleSingIn.id ?: "-1")
    }

    override fun getErrorMessage(error: String?): String {
        return when (error) {
            else -> context.getString(R.string.unknown)
        }
    }
}