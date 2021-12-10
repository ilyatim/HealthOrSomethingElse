package com.example.healtsorsomethingelse.data.database.recipes

import com.example.healtsorsomethingelse.data.BaseUiState

sealed class DialogUiState : BaseUiState() {
    object Idle : DialogUiState()
    object Loading : DialogUiState()
    data class Content(val recipe: Recipe) : DialogUiState()
    data class Error(val message: String?) : DialogUiState()
}

sealed class DialogAction {
    data class LoadData(val id: Int) : DialogAction()
}