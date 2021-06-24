package com.example.healtsorsomethingelse.network

import com.example.healtsorsomethingelse.data.database.recipes.Recipe

/**
 * Interface that help us handle request on the server to get Recipes
 */
interface RecipesApiServiceHelper {
    /**
     * Return list of all recipes
     * @param count count (by default [RECIPES_COUNT_BY_DEFAULT])
     * @return [List]
     */
    suspend fun getRecipes(count: Int = RECIPES_COUNT_BY_DEFAULT): List<Recipe>

    /**
     * Return recipe by id
     * @param id recipe id
     * @return [Recipe]
     */
    suspend fun getRecipeById(id: Int): Recipe

    /**
     * Return favorite recipes
     * @param userId user id
     * @param count count (by default [RECIPES_COUNT_BY_DEFAULT])
     * @return [List]
     */
    suspend fun getFavoriteRecipes(userId: String, count: Int = RECIPES_COUNT_BY_DEFAULT): List<Recipe>

    /**
     * Return vegetarian recipes
     * @param count count (by default [RECIPES_COUNT_BY_DEFAULT])
     * @return [List]
     */
    suspend fun getVegetarianRecipes(count: Int = RECIPES_COUNT_BY_DEFAULT): List<Recipe>

    /**
     * Add new recipe on the server
     * @param recipe recipe obj
     * @return token [String]
     */
    suspend fun addNewRecipe(recipe: Recipe): String
}

private val RECIPES_COUNT_BY_DEFAULT = 20
