package com.example.healtsorsomethingelse.data.notification

sealed class UiState

object Idle : UiState()
object Loading : UiState()
data class Error(val text: String) : UiState()
data class Content(val notifications: List<Notifications>) : UiState()