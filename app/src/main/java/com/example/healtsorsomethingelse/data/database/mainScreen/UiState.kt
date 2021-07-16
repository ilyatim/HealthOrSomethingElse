package com.example.healtsorsomethingelse.data.database.mainScreen

sealed class UiState {
    object Idle : UiState()
    object Loading : UiState()
    data class Content(val content: List<UserDatabaseContent>) : UiState()
    data class Error(val message: String) : UiState()
}

