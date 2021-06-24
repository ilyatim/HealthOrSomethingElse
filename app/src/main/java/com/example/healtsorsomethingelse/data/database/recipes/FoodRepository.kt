package com.example.healtsorsomethingelse.data.database.recipes

import com.example.healtsorsomethingelse.data.database.recipes.RecipeCell
import com.example.healtsorsomethingelse.data.database.recipes.RecipesType

/**
 * Interface for FoodFragment that provide data
 */
interface FoodRepository {
    /**
     * Return recipes by type
     */
    suspend fun getRecipes(type: RecipesType): List<RecipeCell>
}