package com.example.healtsorsomethingelse.utils.database

import com.example.healtsorsomethingelse.data.database.recipes.FoodRepository
import com.example.healtsorsomethingelse.data.database.recipes.RecipeCell
import com.example.healtsorsomethingelse.data.database.recipes.RecipesType
import com.example.healtsorsomethingelse.data.database.recipes.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteRecipesViewModel @Inject constructor(repo: FoodRepository) : BaseFoodViewModel(repo) {

    private val favItemsList: MutableList<RecipeCell> = mutableListOf()

    override fun loadContent() {
        _state.value = UiState.Loading
        launch {
            _state.value = try {
                favItemsList.addAll(repo.getRecipes(RecipesType.Favorite))
                UiState.Content(favItemsList)
            } catch (e: Exception) {
                UiState.Error(e.message)
            }
        }
    }
}