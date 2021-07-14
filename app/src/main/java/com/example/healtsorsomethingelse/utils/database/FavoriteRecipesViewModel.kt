package com.example.healtsorsomethingelse.utils.database

import com.example.healtsorsomethingelse.data.database.recipes.FoodRepository
import com.example.healtsorsomethingelse.data.database.recipes.RecipeCell
import com.example.healtsorsomethingelse.data.database.recipes.RecipesType
import com.example.healtsorsomethingelse.data.database.recipes.UiState
import com.example.healtsorsomethingelse.utils.ErrorHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.logging.ErrorManager
import javax.inject.Inject

@HiltViewModel
class FavoriteRecipesViewModel @Inject constructor(
    repo: FoodRepository,
    private val errorHandler: ErrorHandler
) : BaseFoodViewModel(repo) {

    private val favItemsList: MutableList<RecipeCell> = mutableListOf()

    override fun loadContent() {
        stateData.value = UiState.Loading
        launch {
            stateData.value = try {
                favItemsList.addAll(repo.getRecipes(RecipesType.Favorite))
                UiState.Content(favItemsList)
            } catch (e: Exception) {
                UiState.Error(errorHandler.getErrorMessage(e.message))
            }
        }
    }
}