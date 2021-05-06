package com.example.healtsorsomethingelse.data.profile

sealed class UiAction {
    data class CompletePurposes(val purpose: String, val listPosition: Int) : UiAction()
    data class DismissPurpose(val listPosition: Int) : UiAction()
    data class AddNewPurpose(val purpose: String) : UiAction()

    object Loading : UiAction()
}