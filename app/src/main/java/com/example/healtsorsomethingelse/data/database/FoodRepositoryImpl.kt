package com.example.healtsorsomethingelse.data.database

import android.util.Log
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class FoodRepositoryImpl @Inject constructor() : FoodRepository {

    override fun getRecipes(type: RecipesType): List<RecipeCell> {
        return when (type) {
            RecipesType.All -> getAllRecipes()
            RecipesType.Favorite -> getFavoriteRecipes()
            RecipesType.Vegetarian -> getVegetarianRecipes()
        }
    }

    private fun getAllRecipes(): List<RecipeCell> {
        return listOf(
            getMockRecipe(),
            getMockRecipe(),
            getMockRecipe(),
            getMockRecipe(),
            getMockRecipe(),
            getMockRecipe(),
        )
    }

    private fun getFavoriteRecipes(): List<RecipeCell> {
        return listOf(

        )
    }

    private fun getVegetarianRecipes(): List<RecipeCell> {
        return listOf(

        )
    }

    private fun getMockRecipe(): RecipeCell {
        return RecipeCell(
            "Кофе для Максима",
            "https://i1.wallbox.ru/wallpapers/main/201547/18a7c1c34ac29f8.jpg",
            "Любимое",
            12,
            2000,
            2
        )
    }
}