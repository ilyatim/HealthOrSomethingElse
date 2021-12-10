package com.example.healtsorsomethingelse.utils

import androidx.lifecycle.ViewModel
import com.example.healtsorsomethingelse.data.BaseUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

abstract class BaseViewModel/*<out T : BaseUiState, in K>*/ : ViewModel(), CoroutineScope by MainScope() {
    /*private val uiState: MutableStateFlow<T> = MutableStateFlow(BaseUiState.Idle)

    abstract fun getUiState(): StateFlow<T>
    abstract fun sendAction(action: K)*/
}