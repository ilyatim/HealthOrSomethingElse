package com.example.core

sealed class BaseUiState {
    abstract val idle: BaseUiState
}