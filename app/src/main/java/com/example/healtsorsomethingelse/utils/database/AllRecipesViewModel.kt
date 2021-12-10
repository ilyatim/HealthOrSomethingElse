package com.example.healtsorsomethingelse.utils.database

import com.example.healtsorsomethingelse.data.database.recipes.FoodRepository
import com.example.healtsorsomethingelse.data.database.recipes.RecipeCell
import com.example.healtsorsomethingelse.data.database.recipes.RecipesType
import com.example.healtsorsomethingelse.data.database.recipes.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllRecipesViewModel @Inject constructor(repo: FoodRepository) : BaseFoodViewModel(repo) {

    private val allItemsList: MutableList<RecipeCell> = mutableListOf()

    override fun loadContent() {
        setState(UiState.Loading)
        launch {
            val state = try {
                allItemsList.addAll(repo.getRecipes(RecipesType.All))
                UiState.Content(allItemsList)
            } catch (e: Exception) {
                UiState.Error(e.message)
            }
            setState(state)
        }
    }
}