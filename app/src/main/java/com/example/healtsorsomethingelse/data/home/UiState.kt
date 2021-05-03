package com.example.healtsorsomethingelse.data.home

sealed class UiState {
    object Idle : UiState()
    object Loading : UiState()

    data class Content(val todayRate: Int, val list: List<Statistics>) : UiState()
}