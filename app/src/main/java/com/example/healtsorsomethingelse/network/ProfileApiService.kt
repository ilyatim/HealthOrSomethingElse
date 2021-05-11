package com.example.healtsorsomethingelse.network

import retrofit2.http.GET
import retrofit2.http.POST

interface ProfileApiService {
    @POST
    suspend fun addNewPurpose(purpose: String, userId: Int)
    @POST
    suspend fun addCompletedPurpose(purpose: String, userId: Int)
    @GET("/profile/{user_id}/user_weight_purpose")
    suspend fun getUserWeightPurpose(userId: Int): String
    @GET("/profile/{user_id}/user_height")
    suspend fun getUserHeight(userId: Int): Int
    @GET("/profile/{user_id}/user_weight")
    suspend fun getUserWeight(userId: Int): Double
    @GET("/profile/{user_id}/fat_percentage")
    suspend fun getUserFatPercentage(userId: Int): Double
    @GET("profile/{user_id}/user_purposes")
    suspend fun getUserPurposes(userId: Int): List<String>
}