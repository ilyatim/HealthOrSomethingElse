package com.example.healtsorsomethingelse.data.database

sealed class DialogUiState {
    object Idle : DialogUiState()
    object Loading : DialogUiState()
    data class Content(val dialogData: DialogData) : DialogUiState()
    data class Error(val message: String) : DialogUiState()
}

sealed class DialogAction {
    data class LoadData(val id: Int) : DialogAction()
}

data class DialogData(
    val id: Int,
    val imageUrl: Int,
    val name: String,
    val description: String,
    val calories: Int,
    val grams: Int,
    val protein: Int,
    val fats: Int,
    val carbohydrates: Int,
    val likes: Int,
    val cookingTime: Long,
    val portion: Int
)