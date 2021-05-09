package com.example.healtsorsomethingelse.data.database

import com.example.healtsorsomethingelse.network.DatabaseApiServiceHelper
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class FoodRepositoryImpl @Inject constructor(
    private val networkServiceHelper: DatabaseApiServiceHelper
) : FoodRepository {

    override suspend fun getRecipes(type: RecipesType): List<RecipeCell> {
        return when (type) {
            RecipesType.All -> getAllRecipes()
            RecipesType.Favorite -> getFavoriteRecipes()
            RecipesType.Vegetarian -> getVegetarianRecipes()
        }
    }

    private suspend fun getAllRecipes(): List<RecipeCell> {
        val recipes = networkServiceHelper.getRecipes()
        return convertToCellData(recipes)
    }

    private suspend fun getFavoriteRecipes(): List<RecipeCell> {
        val recipes = networkServiceHelper.getFavoriteRecipes()
        return convertToCellData(recipes)
    }

    private suspend fun getVegetarianRecipes(): List<RecipeCell> {
        val recipes = networkServiceHelper.getVegetarianRecipes()
        return convertToCellData(recipes)
    }

    private fun convertToCellData(recipes: List<Recipe>): List<RecipeCell> {
        return recipes.map {
            RecipeCell(
                id = it.id,
                name = it.name,
                imageUrl = it.imageUrl,
                category = it.category,
                likes = it.likes,
                cookingTime = it.cookingTime,
                portion = it.portion
            )
        }
    }
}
