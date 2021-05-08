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
class VegetarianRecipesViewModel @Inject constructor(repo: FoodRepository) : BaseFoodViewModel(repo) {

    private val vegItemsList: MutableList<RecipeCell> = mutableListOf()

    override fun showBottomSheetFragment(id: Int) {

    }

    override fun loadContent() {
        _state.value = UiState.Loading
        launch {
            _state.value = try {
                vegItemsList.addAll(repo.getRecipes(RecipesType.Vegetarian))
                UiState.Content(vegItemsList)
            } catch (e: Exception) {
                UiState.Error(e.message)
            }
        }
    }
}