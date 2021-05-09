package com.example.healtsorsomethingelse.utils.database

import android.util.Log
import com.example.healtsorsomethingelse.data.database.FoodRepository
import com.example.healtsorsomethingelse.data.database.RecipeCell
import com.example.healtsorsomethingelse.data.database.RecipesType
import com.example.healtsorsomethingelse.data.database.UiState
import com.example.healtsorsomethingelse.utils.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllRecipesViewModel @Inject constructor(repo: FoodRepository) : BaseFoodViewModel(repo) {

    private val allItemsList: MutableList<RecipeCell> = mutableListOf()

    override fun loadContent() {
        _state.value = UiState.Loading
        launch {
            _state.value = try {
                allItemsList.addAll(repo.getRecipes(RecipesType.All))
                UiState.Content(allItemsList)
            } catch (e: Exception) {
                UiState.Error(e.message)
            }
        }
    }
}