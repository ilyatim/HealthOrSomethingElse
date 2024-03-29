package com.example.healtsorsomethingelse.data.profile

sealed class UiState {
    object Idle : UiState()
    object Loading : UiState()
    data class Content(val content: ProfileData) : UiState()
    data class Error(val message: String?) : UiState()
}