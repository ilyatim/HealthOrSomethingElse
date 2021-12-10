package com.example.healtsorsomethingelse.utils.database

import com.example.healtsorsomethingelse.data.BaseUiState
import com.example.healtsorsomethingelse.data.database.recipes.FoodRepository
import com.example.healtsorsomethingelse.data.database.recipes.UiAction
import com.example.healtsorsomethingelse.data.database.recipes.UiState
import com.example.healtsorsomethingelse.utils.BaseViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

abstract class BaseFoodViewModel(
    protected val repo: FoodRepository
) : BaseViewModel<UiState, UiAction>(UiState.Idle) {

    override fun collectAction(action: UiAction) {
        TODO("Not yet implemented")
    }

    protected abstract fun loadContent()
}