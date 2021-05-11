package com.example.healtsorsomethingelse.network

interface ProfileApiServiceHelper {
    suspend fun addNewPurpose(purpose: String, userId: Int)
    suspend fun addCompletedPurpose(purpose: String, userId: Int)
    suspend fun getUserWeightPurpose(userId: Int): String
    suspend fun getUserHeight(userId: Int): Int
    suspend fun getUserWeight(userId: Int): Double
    suspend fun getUserFatPercentage(userId: Int): Double
    suspend fun getUserPurposes(userId: Int): List<String>
}