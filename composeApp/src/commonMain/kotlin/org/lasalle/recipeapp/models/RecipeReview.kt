package org.lasalle.recipeapp.models

import kotlinx.serialization.Serializable

@Serializable
data class RecipeReview(
    // Agrega ? al tipo y = null al final de CADA CAMPO
    val category: String? = null,
    val imageUrl: String? = null,
    val ingredients: List<String> = emptyList(), // Para listas usa emptyList()
    val instructions: List<String> = emptyList(),
    val minutes: Int? = 0,
    val prompt: String? = null,
    val stars: Int? = 0,
    val title: String? = null
)