package org.lasalle.recipeapp.data.services

import de.jensklingenberg.ktorfit.http.Body
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.POST
import de.jensklingenberg.ktorfit.http.Query
import org.lasalle.recipeapp.models.Prompt
import org.lasalle.recipeapp.models.Recipe
import org.lasalle.recipeapp.models.RecipeReview

interface RecipeService {

    @GET("recipes")
    suspend fun getRecipeByUserId( @Query("userId") userId : Int) : List<Recipe>

    @POST("recipes/ai-generate")
    suspend fun generateRecipe(@Body request: Prompt) : RecipeReview

    @POST("recipes")
    suspend fun saveRecipeInDb(@Body request: Recipe) : Recipe
}