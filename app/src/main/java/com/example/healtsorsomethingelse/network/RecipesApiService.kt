package com.example.healtsorsomethingelse.network

import com.example.healtsorsomethingelse.data.database.recipes.Recipe
import retrofit2.http.*

interface RecipesApiService {
    @GET("/recipes/all_recipes")
    suspend fun getRecipes(@Query("limit")count: Int): List<Recipe>
    @GET("/recipes/{id}")
    suspend fun getRecipeByID(@Path("id")id: Int): Recipe
    @GET("/recipes/{user_id}/favorite_recipes")
    suspend fun getFavoriteRecipes(@Path("user_id")user_id: String, @Query("limit")count: Int): List<Recipe>
    @GET("/recipes/vegetable_recipes")
    suspend fun getVegetableRecipes(@Query("limit")count: Int): List<Recipe>
    @POST("/recipes/add_recipe")
    suspend fun addNewRecipe(@Body recipe: Recipe): String
}