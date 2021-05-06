package com.example.healtsorsomethingelse.data.database

interface FoodRepository {
    fun getRecipes(): List<RecipeCell>
}