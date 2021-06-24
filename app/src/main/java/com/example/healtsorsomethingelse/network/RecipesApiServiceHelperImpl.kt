package com.example.healtsorsomethingelse.network

import com.example.healtsorsomethingelse.data.database.recipes.Recipe
import javax.inject.Inject

class RecipesApiServiceHelperImpl @Inject constructor(
    private val apiService: RecipesApiService
) : RecipesApiServiceHelper {

    override suspend fun getRecipes(count: Int): List<Recipe> {
        return apiService.getRecipes(count)
    }

    override suspend fun getRecipeById(id: Int): Recipe {
        return apiService.getRecipeByID(id)
    }

    override suspend fun getFavoriteRecipes(userId: String, count: Int): List<Recipe> {
        return apiService.getFavoriteRecipes(userId, count)
    }

    override suspend fun getVegetarianRecipes(count: Int): List<Recipe> {
        return apiService.getVegetableRecipes(count)
    }

    override suspend fun addNewRecipe(recipe: Recipe): String {
        return apiService.addNewRecipe(recipe)
    }

}