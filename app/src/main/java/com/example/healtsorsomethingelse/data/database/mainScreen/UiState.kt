package com.example.healtsorsomethingelse.data.database.mainScreen

import com.example.healtsorsomethingelse.data.BaseUiState

sealed class UiState : BaseUiState() {
    object Idle : UiState()
    object Loading : UiState()
    data class Content(val content: List<UserDatabaseContent>, val height: Int) : UiState()
    data class Error(val message: String) : UiState()
}

