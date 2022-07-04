package com.example.healtsorsomethingelse.utils.database

import com.example.core.utils.BaseViewModel
import com.example.healtsorsomethingelse.data.database.DialogRepository
import com.example.healtsorsomethingelse.data.database.recipes.DialogAction
import com.example.healtsorsomethingelse.data.database.recipes.DialogUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BottomDialogViewModel @Inject constructor(
    private val repo: DialogRepository
): BaseViewModel<DialogUiState, DialogAction, Unit>() {

    override val uiState: MutableStateFlow<DialogUiState>
        get() = MutableStateFlow(DialogUiState.Idle)

    private fun initLoading(id: Int) {
        setState(DialogUiState.Loading)
        launch {
            val state = try {
                DialogUiState.Content(repo.getRecipeByID(id))
            } catch (e: Exception) {
                DialogUiState.Error(e.message)
            }
            setState(state)
        }
    }

    override fun collectAction(action: DialogAction) {
        when (action) {
            is DialogAction.LoadData -> initLoading(action.id)
        }
    }
}