package com.example.healtsorsomethingelse.utils.database

import androidx.lifecycle.ViewModel
import com.example.healtsorsomethingelse.data.database.FoodRepository
import com.example.healtsorsomethingelse.data.database.RecipeCell
import com.example.healtsorsomethingelse.data.database.RecipesType
import com.example.healtsorsomethingelse.data.database.UiState
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