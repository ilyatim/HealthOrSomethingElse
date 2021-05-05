package com.example.healtsorsomethingelse.data.profile

sealed class UiState {
    object Idle : UiState()
    object Loading : UiState()
    data class Content(val plug: String) : UiState()
}