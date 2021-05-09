package com.example.healtsorsomethingelse.data.database

import com.example.healtsorsomethingelse.network.DatabaseApiServiceHelper
import javax.inject.Inject

class DialogRepositoryImpl @Inject constructor(
    private val networkServiceHelper: DatabaseApiServiceHelper
) : DialogRepository {
    override suspend fun getRecipeByID(id: Int): Recipe {
        return networkServiceHelper.getRecipeById(id)
    }

    override suspend fun getRecipes(): List<Recipe> {
        return networkServiceHelper.getRecipes()
    }
}
