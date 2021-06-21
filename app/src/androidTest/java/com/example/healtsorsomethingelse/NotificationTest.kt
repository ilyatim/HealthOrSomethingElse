package com.example.healtsorsomethingelse

import androidx.test.platform.app.InstrumentationRegistry
import com.example.healtsorsomethingelse.data.notification.NotificationTopic
import com.example.healtsorsomethingelse.data.notification.Notifications
import com.example.healtsorsomethingelse.data.notification.UserNotification
import com.example.healtsorsomethingelse.utils.notifications.NotificationRepositoryHelper
import com.example.healtsorsomethingelse.utils.notifications.NotificationRepositoryHelperImpl
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import javax.inject.Inject

/*@HiltAndroidTest*/
class NotificationTests {

    /*@Inject*/
    lateinit var helper: NotificationRepositoryHelper

    val testList: MutableList<Notifications> = mutableListOf()
    val expectedList: MutableList<Notifications> = mutableListOf()

    @Before
    fun fillList () {
        helper = NotificationRepositoryHelperImpl(InstrumentationRegistry.getInstrumentation().targetContext)
        //hiltRule.inject()
        testList.add(UserNotification("1000001", "ff", "ff", System.currentTimeMillis(), "ff"))
        testList.add(UserNotification("1000001", "ff", "ff", System.currentTimeMillis() - 60 * 60 * 24 * 1000, "ff"))
        testList.add(UserNotification("1000001", "ff", "ff", System.currentTimeMillis() - 6 * 60 * 60 * 24 * 1000, "ff"))
        testList.add(UserNotification("1000001", "ff", "ff", System.currentTimeMillis() - 20 * 60 * 60 * 24 * 1000, "ff"))

        expectedList.addAll(testList)
        expectedList.add(0, NotificationTopic("Сегодня"))
        expectedList.add(2, NotificationTopic("Вчера"))
        expectedList.add(4, NotificationTopic("На прошлой неделе"))
        expectedList.add(6, NotificationTopic("В прошлой месяце"))
    }
    @Test
    fun testGetDateText () {
        //val helper = NotificationRepositoryHelperImpl()
        /*val test1: String = */helper.addDateToNotification(testList)
        Assert.assertEquals(expectedList, testList)
    }
}
