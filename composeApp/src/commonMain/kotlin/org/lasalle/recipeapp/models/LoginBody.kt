package org.lasalle.recipeapp.models

import kotlinx.serialization.Serializable

@Serializable
data class LoginBody(
    val email : String,
    val password : String
)
