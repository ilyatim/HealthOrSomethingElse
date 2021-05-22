package com.example.healtsorsomethingelse.network

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ProfileApiService {
    @POST("/profile/{user_id}/add_new_purpose")
    suspend fun addNewPurpose(@Body purpose: String, @Path("user_id")userId: String): String
    @POST("/profile/{user_id}/complete_purpose")
    suspend fun addCompletedPurpose(@Body purpose: String, @Path("user_id")userId: String): String
    @GET("/profile/{user_id}/user_weight_purpose")
    suspend fun getUserWeightPurpose(userId: String): String
    @GET("/profile/{user_id}/user_height")
    suspend fun getUserHeight(userId: String): Int
    @GET("/profile/{user_id}/user_weight")
    suspend fun getUserWeight(userId: String): Double
    @GET("/profile/{user_id}/fat_percentage")
    suspend fun getUserFatPercentage(userId: String): Double
    @GET("profile/{user_id}/user_purposes")
    suspend fun getUserPurposes(userId: String): List<String>
}