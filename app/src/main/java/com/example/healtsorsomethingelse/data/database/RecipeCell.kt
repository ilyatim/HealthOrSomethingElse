package com.example.healtsorsomethingelse.data.database

data class RecipeCell(
    val name: String,
    val imageUrl: String,
    val likes: Int,
    val cookingTime: Long,
    val portion: Short
)