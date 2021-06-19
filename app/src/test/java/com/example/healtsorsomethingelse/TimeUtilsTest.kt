package com.example.healtsorsomethingelse

import com.example.healtsorsomethingelse.utils.TimeUtils
import org.junit.Assert
import org.junit.Test


class TimeUtilsTest {
    private val oneMinute: Long = 1000 * 60
    private val oneHour: Long = 1000 * 60 * 60
    private val oneDay: Long = 24 * 60 * 60 * 1000

    @Test
    fun testTimeUtils() {
        val test1 = TimeUtils.getDayCountFromDate(oneDay)
        val test2 = TimeUtils.getDayCountFromDate(oneDay * 7)
        val test3 = TimeUtils.getDayCountFromDate(oneDay * 50)
        val test4 = TimeUtils.getDayCountFromDate(60 * 60 * 32)
        val test5 = TimeUtils.getDayCountFromDate(12341235432)

        Assert.assertEquals(1, test1)
        Assert.assertEquals(7, test2)
        Assert.assertEquals(50, test3)
        Assert.assertEquals(0, test4)
        Assert.assertEquals(31 + 28 + 31 + 30 + 22, test5)
        //Assert.assertEquals(1, TimeUtils.getDayCountFromDate(System.currentTimeMillis()))
    }

    @Test
    fun testCookingTime() {
        val test1 = TimeUtils.getCookingTime(oneMinute)
        val test2 = TimeUtils.getCookingTime(oneHour)
        val test3 = TimeUtils.getCookingTime(oneMinute + oneHour + oneMinute)

        Assert.assertEquals("00:00", TimeUtils.getCookingTime(0))
        Assert.assertEquals("00:01", test1)
        Assert.assertEquals("01:00", test2)
        Assert.assertEquals("01:02", test3)
    }

    @Test
    fun testParseNotificationDate() {
        val test1 = TimeUtils.parseNotificationDate(0)
        val test2 = TimeUtils.parseNotificationDate(1609452220000)
        val test3 = TimeUtils.parseNotificationDate(1624060800000)

        Assert.assertEquals("1 января 03:00", test1)
        Assert.assertEquals("1 января 01:03", test2)
        Assert.assertEquals("19 июня 03:00", test3)
    }
}