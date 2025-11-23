package org.lasalle.recipeapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform