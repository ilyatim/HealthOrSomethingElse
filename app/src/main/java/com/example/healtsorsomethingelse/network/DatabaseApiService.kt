package com.example.healtsorsomethingelse.network

import com.example.healtsorsomethingelse.data.database.Recipe
import com.google.gson.annotations.SerializedName
import retrofit2.http.GET
import retrofit2.http.Path

interface DatabaseApiService {
    @GET("/recipes/all_recipes")
    suspend fun getRecipes(): List<Recipe>
    @GET("/recipes/{id}")
    suspend fun getRecipeByID(@Path("id")id: Int): Recipe
    @GET("/recipes/favorite_recipes")
    suspend fun getFavoriteRecipes(): List<Recipe>
    @GET("/recipes/vegetable_recipes")
    suspend fun getVegetableRecipes(): List<Recipe>
}