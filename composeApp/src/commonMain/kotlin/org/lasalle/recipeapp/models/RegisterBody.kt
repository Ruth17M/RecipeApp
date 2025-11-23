package org.lasalle.recipeapp.models

import kotlinx.serialization.Serializable

@Serializable
data class RegisterBody(
    val name : String,
    val email : String,
    val password : String
)
