package com.example.healtsorsomethingelse.data.notification

sealed class Actions

object Load : Actions()
data class RemoveNotification(val id: String) : Actions()