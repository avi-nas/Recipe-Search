package com.example.recipesearch.network

import android.util.Log
import com.example.recipesearch.models.CacheRecipes
import com.example.recipesearch.models.Recipes
import com.example.recipesearch.models.recipe.Recipe
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query


interface Spoonacular {
    @GET("/recipes/complexSearch")
    suspend fun fetchRecipes(
        @Header("Content-Type") contentType: String,
        @Query("apiKey") apiKey: String
    ): Recipes

     @GET("/recipes/{id}/information")
    suspend fun getRecipeData(
        @Header("Content-Type") contentType: String,
        @Path("id") id: Int,
         @Query("apiKey") apiKey: String
    ): Recipe
}


class SpoonacularApi {
    private val apiKey = "ee8149dac9a04624bfa78becf82a1595"
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.spoonacular.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val apiService = retrofit.create(Spoonacular::class.java)
    private var recipesCache: CacheRecipes? = null
    private val cacheDuration = 15 * 60 * 1000 // 15 minutes in milliseconds


    suspend fun getRecipes(): Result<Recipes> {
        val currentTime = System.currentTimeMillis()

        // Check if the cache is valid
        recipesCache?.let {
            if (currentTime - it.timestamp < cacheDuration) {
                Log.d("TAG", "recipesCache: ${it.data}")
                return Result.success(it.data)
            }
        }


        return try {
            val response = apiService.fetchRecipes("application/json", apiKey)
            recipesCache = CacheRecipes(response, currentTime)

            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    suspend fun getRecipeData(id: Int): Result<Recipe> {
        return try {
            val response = apiService.getRecipeData("application/json", id, apiKey)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}