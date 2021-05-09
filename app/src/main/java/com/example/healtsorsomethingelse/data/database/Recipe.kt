package com.example.healtsorsomethingelse.data.database

data class Recipe(
    val id: Int,
    val name: String,
    val description: String,
    val category: String,
    val imageUrl: String,
    val calories: Int,
    val grams: Int,
    val fats: Int,
    val protein: Int,
    val carbohydrates: Int,
    val likes: Int,
    val cookingTime: Long,
    val portion: Short
)