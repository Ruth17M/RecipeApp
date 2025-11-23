package org.lasalle.recipeapp.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable

@Composable
fun RecipeTheme(
    useDarkTheme : Boolean = isSystemInDarkTheme(),
    content : @Composable () -> Unit
) {
    MaterialTheme (
        colorScheme = if(useDarkTheme) DarkColors else LightColors,
        content = content
    )
}