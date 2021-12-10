package com.example.healtsorsomethingelse.data.database.recipes

import com.example.healtsorsomethingelse.data.BaseUiState

sealed class UiState : BaseUiState() {
    object Idle : UiState()
    object Loading : UiState()
    data class Content(val items: List<RecipeCell>) : UiState()
    data class Error(val error: String?) : UiState()
}