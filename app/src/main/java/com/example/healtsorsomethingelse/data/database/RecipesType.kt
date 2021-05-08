package com.example.healtsorsomethingelse.data.database

sealed class RecipesType {
    object All : RecipesType()
    object Favorite : RecipesType()
    object Vegetarian : RecipesType()
}
