package com.example.healtsorsomethingelse.network

import com.example.healtsorsomethingelse.data.notification.UserNotification
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
    suspend fun getUserWeightPurpose(@Path("user_id")userId: String): Int
    @GET("/profile/{user_id}/user_height")
    suspend fun getUserHeight(@Path("user_id")userId: String): Int
    @GET("/profile/{user_id}/user_weight")
    suspend fun getUserWeight(@Path("user_id")userId: String): Double
    @GET("/profile/{user_id}/fat_percentage")
    suspend fun getUserFatPercentage(@Path("user_id")userId: String): Double
    @GET("/profile/{user_id}/user_purposes")
    suspend fun getUserPurposes(@Path("user_id")userId: String): List<String>
    @GET("/profile/{user_id}/get_all_notifications")
    suspend fun getUserNotification(@Path("user_id")userId: String): List<UserNotification>
}