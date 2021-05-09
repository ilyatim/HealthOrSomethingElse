package com.example.healtsorsomethingelse.network

import com.example.healtsorsomethingelse.data.database.Recipe
import com.example.healtsorsomethingelse.network.DatabaseApiService
import com.example.healtsorsomethingelse.network.DatabaseApiServiceHelper
import javax.inject.Inject

class DatabaseApiServiceHelperImpl @Inject constructor(
    private val apiService: DatabaseApiService
) : DatabaseApiServiceHelper {
    override suspend fun getRecipes(): List<Recipe> {
        return apiService.getRecipes()
    }

    override suspend fun getRecipeById(id: Int): Recipe {
        return apiService.getRecipeByID(id)
    }

    override suspend fun getFavoriteRecipes(): List<Recipe> {
        return apiService.getFavoriteRecipes()
    }

    override suspend fun getVegetarianRecipes(): List<Recipe> {
        return apiService.getVegetableRecipes()
    }
}