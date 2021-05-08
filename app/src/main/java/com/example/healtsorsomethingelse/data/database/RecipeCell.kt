package com.example.healtsorsomethingelse.data.database

data class RecipeCell(
    val name: String,
    val id: Int,
    val imageUrl: String,
    val category: String,
    val likes: Int,
    val cookingTime: Long,
    val portion: Short
)