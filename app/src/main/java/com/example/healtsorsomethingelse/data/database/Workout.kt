package com.example.healtsorsomethingelse.data.database

data class Workout(
    val workoutId: Int,
    val name: String,
    val description: String,
    val type: String,
    val authorId: Int,
    val difficulty: Short,
    val duration: Long,
)
