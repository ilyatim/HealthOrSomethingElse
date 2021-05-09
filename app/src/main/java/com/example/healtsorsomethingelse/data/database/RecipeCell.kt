package com.example.healtsorsomethingelse.data.database

data class RecipeCell(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val category: String,
    val likes: Int,
    val cookingTime: Long,
    val portion: Short
)