package com.example.healtsorsomethingelse.data.home

import com.example.healtsorsomethingelse.data.BaseUiState

sealed class UiState : BaseUiState() {
    object Idle : UiState()
    object Loading : UiState()

    data class Content(
        val todayRate: Int,
        val list: List<Statistics>,
        val availabilityOfNotifications: Boolean
    ) : UiState()
}