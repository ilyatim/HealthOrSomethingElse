package com.example.healtsorsomethingelse.utils.profile

import com.example.core.utils.BaseViewModel
import com.example.healtsorsomethingelse.data.profile.ProfileData
import com.example.healtsorsomethingelse.data.profile.ProfileRepository
import com.example.healtsorsomethingelse.data.profile.UiAction
import com.example.healtsorsomethingelse.data.profile.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileFragmentViewModel @Inject constructor(
    private val repo: ProfileRepository
) : BaseViewModel<UiState, UiAction, Unit>() {
    override val uiState: MutableStateFlow<UiState>
        get() = MutableStateFlow(UiState.Idle)

    private lateinit var profileData: ProfileData

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
        setState(UiState.Content(profileData))
    }

    private fun completePurpose(purpose: String, listPosition: Int) {
        CoroutineScope(Dispatchers.IO).launch { repo.savePurposes(purpose) }
        removePurpose(listPosition)
    }

    private fun removePurpose(listPosition: Int) {
        profileData = profileData.copy(
            purposes = profileData.purposes.toMutableList().apply { removeAt(listPosition) }
        )
        setState(UiState.Content(profileData))
    }

    private fun initContent() {
        setState(UiState.Loading)
        launch {
            val state = try {
                profileData = repo.getProfileData()
                UiState.Content(profileData)
            } catch (e: Exception) {
                UiState.Error(e.message)
            }
            setState(state)
        }
    }

    override fun collectAction(action: UiAction) {
        when (action) {
            is UiAction.CompletePurposes -> completePurpose(action.purpose, action.listPosition)
            is UiAction.DismissPurpose -> removePurpose(action.listPosition)
            is UiAction.AddNewPurpose -> addNewPurpose(action.purpose)
            UiAction.Loading -> initContent()
        }
    }
}