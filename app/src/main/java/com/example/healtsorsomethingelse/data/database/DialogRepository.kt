package com.example.healtsorsomethingelse.data.database

/**
 * Interface that provide data to bottom dialog sheet
 * in database fragment
 */
interface DialogRepository {

    /**
     * Return recipe by id
     * @param id id of recipe
     * @return [Recipe]
     */
    suspend fun getRecipeByID(id: Int): Recipe

    /**
     * Return all recipes
     * @return [List]
     */
    suspend fun getRecipes(): List<Recipe>
}
