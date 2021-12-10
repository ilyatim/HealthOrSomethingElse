package com.example.healtsorsomethingelse.utils.database

import com.example.healtsorsomethingelse.data.database.recipes.DialogAction
import com.example.healtsorsomethingelse.data.database.DialogRepository
import com.example.healtsorsomethingelse.data.database.recipes.DialogUiState
import com.example.healtsorsomethingelse.utils.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BottomDialogViewModel @Inject constructor(
    private val repo: DialogRepository
): BaseViewModel<DialogUiState, DialogAction>(DialogUiState.Idle) {

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