package com.example.healtsorsomethingelse.data.database

sealed class UiAction {
    object Loading : UiAction()
    data class ShowBottomSheet(val id: Int) : UiAction()
}

