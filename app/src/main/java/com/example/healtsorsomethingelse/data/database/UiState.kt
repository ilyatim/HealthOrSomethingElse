package com.example.healtsorsomethingelse.data.database

sealed class UiState {
    object Idle : UiState()
    object Loading : UiState()
    data class Content(val items: List<RecipeCell>) : UiState()
    data class Error(val error: String?) : UiState()
}