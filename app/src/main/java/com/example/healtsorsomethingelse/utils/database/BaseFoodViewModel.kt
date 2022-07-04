package com.example.healtsorsomethingelse.utils.database

import com.example.core.utils.BaseViewModel
import com.example.healtsorsomethingelse.data.database.recipes.FoodRepository
import com.example.healtsorsomethingelse.data.database.recipes.UiAction
import com.example.healtsorsomethingelse.data.database.recipes.UiState
import kotlinx.coroutines.flow.MutableStateFlow

abstract class BaseFoodViewModel(
    protected val repo: FoodRepository
) : BaseViewModel<UiState, UiAction, Unit>() {

    override val uiState: MutableStateFlow<UiState>
        get() = MutableStateFlow(UiState.Idle)

    override fun collectAction(action: UiAction) {
        TODO("Not yet implemented")
    }

    protected abstract fun loadContent()
}