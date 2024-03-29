package com.example.healtsorsomethingelse.utils.database

import android.util.Log
import com.example.core.utils.BaseViewModel
import com.example.healtsorsomethingelse.data.database.mainScreen.Actions
import com.example.healtsorsomethingelse.data.database.mainScreen.DatabaseRepository
import com.example.healtsorsomethingelse.data.database.mainScreen.UiState
import com.example.healtsorsomethingelse.utils.GoogleNotFountException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DatabaseViewModel @Inject constructor(
    private val repo: DatabaseRepository
): BaseViewModel<UiState, Actions, Unit>() {
    override val uiState: MutableStateFlow<UiState>
        get() = MutableStateFlow(UiState.Idle)

    private val TAG: String = "DatabaseViewModelTag"
    private var height: Int = 0

    private fun loadContent() {
        CoroutineScope(Dispatchers.IO).launch {
            val state = try {
                UiState.Content(repo.getContent(), height)
            } catch (e: GoogleNotFountException) {
                Log.d(TAG, "google not found exception")
                UiState.Error(e.message)
            } catch (e: Exception) {
                Log.d(TAG, "exception - ${e.message}")
                UiState.Error(e.message ?: "Unknown message")
            }

        }
    }

    override fun collectAction(action: Actions) {
        when (action) {
            Actions.LoadContent -> loadContent()
            is Actions.SetBarHeight -> height = action.height
        }
    }
}