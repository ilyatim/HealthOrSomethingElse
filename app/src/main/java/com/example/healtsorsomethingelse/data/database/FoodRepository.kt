package com.example.healtsorsomethingelse.data.database

/**
 * Interface for FoodFragment that provide data
 */
interface FoodRepository {
    /**
     * Return recipes by type
     */
    suspend fun getRecipes(type: RecipesType): List<RecipeCell>
}