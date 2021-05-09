package com.example.healtsorsomethingelse.network

import com.example.healtsorsomethingelse.data.database.Recipe

interface DatabaseApiServiceHelper {
    suspend fun getRecipes(): List<Recipe>
    suspend fun getRecipeById(id: Int): Recipe
    suspend fun getFavoriteRecipes(): List<Recipe>
    suspend fun getVegetarianRecipes(): List<Recipe>
}