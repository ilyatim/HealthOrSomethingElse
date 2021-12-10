package com.example.healtsorsomethingelse.utils.database

import com.example.healtsorsomethingelse.data.database.recipes.FoodRepository
import com.example.healtsorsomethingelse.data.database.recipes.RecipeCell
import com.example.healtsorsomethingelse.data.database.recipes.RecipesType
import com.example.healtsorsomethingelse.data.database.recipes.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VegetarianRecipesViewModel @Inject constructor(
    repo: FoodRepository
) : BaseFoodViewModel(repo) {

    private val vegItemsList: MutableList<RecipeCell> = mutableListOf()

    override fun loadContent() {
        setState(UiState.Loading)
        launch {
            val state = try {
                vegItemsList.addAll(repo.getRecipes(RecipesType.Vegetarian))
                UiState.Content(vegItemsList)
            } catch (e: Exception) {
                UiState.Error(e.message)
            }
            setState(state)
        }
    }
}