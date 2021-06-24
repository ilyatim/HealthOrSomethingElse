package com.example.healtsorsomethingelse.data.database.recipes

sealed class RecipesType {
    object All : RecipesType()
    object Favorite : RecipesType()
    object Vegetarian : RecipesType()
}
