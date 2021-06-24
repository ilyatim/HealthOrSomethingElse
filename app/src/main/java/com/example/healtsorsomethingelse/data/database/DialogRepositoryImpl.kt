package com.example.healtsorsomethingelse.data.database

import com.example.healtsorsomethingelse.data.database.recipes.Recipe
import com.example.healtsorsomethingelse.network.RecipesApiServiceHelper
import javax.inject.Inject

class DialogRepositoryImpl @Inject constructor(
    private val networkServiceHelper: RecipesApiServiceHelper
) : DialogRepository {
    override suspend fun getRecipeByID(id: Int): Recipe {
        return networkServiceHelper.getRecipeById(id)
    }

    override suspend fun getRecipes(): List<Recipe> {
        return networkServiceHelper.getRecipes()
    }
}
