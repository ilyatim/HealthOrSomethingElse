package com.example.healtsorsomethingelse.data

sealed class MainScreenState {
    object Idle : MainScreenState()
    object Loading : MainScreenState()

    data class Content(val content: MainScreenContent) : MainScreenState()
    data class Error(val error: String) : MainScreenState()
}
