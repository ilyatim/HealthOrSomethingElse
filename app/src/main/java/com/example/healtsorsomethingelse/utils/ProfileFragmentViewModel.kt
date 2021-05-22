package com.example.healtsorsomethingelse.utils

import android.util.Log
import com.example.healtsorsomethingelse.data.profile.ProfileData
import com.example.healtsorsomethingelse.data.profile.ProfileRepository
import com.example.healtsorsomethingelse.data.profile.UiAction
import com.example.healtsorsomethingelse.data.profile.UiState
import com.example.healtsorsomethingelse.utils.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class ProfileFragmentViewModel @Inject constructor(private val repo: ProfileRepository) : BaseViewModel() {

    private lateinit var profileData: ProfileData

    private val _state: MutableStateFlow<UiState> = MutableStateFlow(UiState.Idle)
    val state: StateFlow<UiState>
        get() = _state

    private val action: Channel<UiAction> = Channel(Channel.UNLIMITED)

    init {
        handleIntent()
    }

    fun sendAction(action: UiAction) {
        launch { this@ProfileFragmentViewModel.action.send(action) }
    }

    private fun handleIntent() {
        launch {
            action.consumeAsFlow().collect {
                when (it) {
                    is UiAction.CompletePurposes -> completePurpose(it.purpose, it.listPosition)
                    is UiAction.DismissPurpose -> removePurpose(it.listPosition)
                    is UiAction.AddNewPurpose -> addNewPurpose(it.purpose)
                    UiAction.Loading -> initContent()
                }
            }
        }
    }

    private fun addNewPurpose(purpose: String) {
        launch {
            repo.addNewPurpose(purpose)
        }
        addPurpose(purpose)
    }

    private fun addPurpose(purpose: String) {
        profileData = profileData.copy(
            purposes = profileData.purposes.toMutableList().apply { add(purpose) }
        )
        _state.value = UiState.Content(profileData)
    }

    private fun completePurpose(purpose: String, listPosition: Int) {
        launch {
            repo.savePurposes(purpose)
        }
        removePurpose(listPosition)
    }

    private fun removePurpose(listPosition: Int) {
        profileData = profileData.copy(
            purposes = profileData.purposes.toMutableList().apply { removeAt(listPosition) }
        )
        _state.value = UiState.Content(profileData)
    }

    private fun initContent() {
        _state.value = UiState.Loading
        launch {
            _state.value = try {
                profileData = repo.getProfileData()
                UiState.Content(profileData)
            } catch (e: Exception) {
                UiState.Error(e.message)
            }
        }
    }
}