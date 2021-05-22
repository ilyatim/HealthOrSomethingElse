package com.example.healtsorsomethingelse.data.profile

data class ProfileData(
    val name: String?,
    val email: String?,
    val imageUrl: String?,
    val weightPurpose: Int,
    val height: Int,
    val weight: Double,
    val fatPercentage: Double,
    val purposes: List<String>
    )
