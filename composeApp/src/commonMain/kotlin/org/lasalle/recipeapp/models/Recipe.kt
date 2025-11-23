package org.lasalle.recipeapp.models

import kotlinx.serialization.Serializable

@Serializable
data class Recipe(
    // CAMBIO CRÍTICO: Ponle ? y = null.
    // Esto permite que si no le mandas valor, Kotlin mande null o lo omita.
    val id: Int? = null,

    val category: String,
    val imageUrl: String, // Ojo aquí, si es muy larga puede fallar también
    val ingredients: List<String>,
    val instructions: List<String>,
    val minutes: Int,
    val stars: Int,
    val title: String,
    val userId: Int
)

