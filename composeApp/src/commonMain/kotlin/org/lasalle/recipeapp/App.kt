package org.lasalle.recipeapp

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.lasalle.recipeapp.ui.HomeScreenRoute
import org.lasalle.recipeapp.ui.LoginScreenRoute
import org.lasalle.recipeapp.ui.RecipeTheme
import org.lasalle.recipeapp.ui.RegisterScreenRoute
import org.lasalle.recipeapp.ui.screeens.auth.LoginScreen
import org.lasalle.recipeapp.ui.screeens.auth.RegisterScreen
import org.lasalle.recipeapp.ui.screeens.home.HomeScreen

@Composable
@Preview
fun App() {
    RecipeTheme {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = LoginScreenRoute
        ){
            composable<LoginScreenRoute>{
                LoginScreen(
                    navController
                )
            }
            composable<RegisterScreenRoute>{
                RegisterScreen(
                    navController
                )
            }
            composable<HomeScreenRoute>{
                HomeScreen(
                    navController
                )
            }
        }
    }
}