package com.example.healtsorsomethingelse.data

sealed class BaseUiState {
    abstract class UiState : BaseUiState()
    object Idle : BaseUiState()
}