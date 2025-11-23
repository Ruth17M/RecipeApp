package org.lasalle.recipeapp.ui

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val PrimaryColorLight = Color(0xFFFFA726)
val SecondaryColorLight = Color(0xFFFFCC80)
val BackgroundColorLight = Color(0xFFFFF3E0)
val SurfaceColorLight = Color(0xFFFFFFFF)
val TextPrimaryLight = Color(0xFF2E2E2E)
val TextSecondaryLight = Color(0xFF6D6D6D)
val OutlineLight = Color(0xFFFFE0B2)

val PrimaryColorDark = Color(0xFFFFB74D)
val SecondaryColorDark = Color(0xFFFFE0B2)
val BackgroundColorDark = Color(0xFF151312)
val SurfaceColorDark = Color(0xFF1C1A18)
val TextPrimaryDark = Color(0xFFF7F2EA)
val TextSecondaryDark = Color(0xFFCFC2B3)
val OutlineDark = Color(0xFF3C342B)

// ===== Light Scheme =====
val LightColors = lightColorScheme(
    primary = PrimaryColorLight,
    secondary = SecondaryColorLight,
    background = BackgroundColorLight,
    surface = SurfaceColorLight,
    onPrimary = Color.White,
    onBackground = TextPrimaryLight,
    onSurface = TextPrimaryLight,
    surfaceVariant = Color(0xFFFFE8C7),
    onSurfaceVariant = TextSecondaryLight,
    outline = OutlineLight
)

val DarkColors = darkColorScheme(
    primary = PrimaryColorDark,
    secondary = SecondaryColorDark,
    background = BackgroundColorDark,
    surface = SurfaceColorDark,
    onPrimary = Color.Black,
    onBackground = TextPrimaryDark,
    onSurface = TextPrimaryDark,
    surfaceVariant = Color(0xFF2A2621),
    onSurfaceVariant = TextSecondaryDark,
    outline = OutlineDark
)

