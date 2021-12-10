package com.example.healtsorsomethingelse.data.profile

import com.example.healtsorsomethingelse.data.BaseUiState

sealed class UiState : BaseUiState() {
    object Idle : UiState()
    object Loading : UiState()
    data class Content(val content: ProfileData) : UiState()
    data class Error(val message: String?) : UiState()
}