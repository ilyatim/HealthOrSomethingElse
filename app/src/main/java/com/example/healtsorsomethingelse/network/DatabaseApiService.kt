package com.example.healtsorsomethingelse.network

import com.example.healtsorsomethingelse.data.database.mainScreen.UserDatabaseContent
import retrofit2.http.GET
import retrofit2.http.Path

interface DatabaseApiService {
    @GET("/database/main_page/block/{user_id}/database_list")
    suspend fun getDatabaseMainPageBlockList(@Path("user_id")userId: String): List<UserDatabaseContent.Block>
    @GET("/database/main_page/list/{user_id}/database_list")
    suspend fun getDatabaseMainPageSublist(@Path("user_id")userId: String): List<UserDatabaseContent.ContentList>
}