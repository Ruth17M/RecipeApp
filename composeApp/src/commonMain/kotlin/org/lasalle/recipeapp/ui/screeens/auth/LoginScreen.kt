package org.lasalle.recipeapp.ui.screeens.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.lasalle.recipeapp.ui.HomeScreenRoute
import org.lasalle.recipeapp.ui.LoginScreenRoute
import org.lasalle.recipeapp.ui.RecipeTheme
import org.lasalle.recipeapp.ui.screeens.home.HomeScreen
import org.lasalle.recipeapp.ui.viewmodels.AuthViewModel

@Composable
fun LoginScreen(navController: NavController){
    val colors = MaterialTheme.colorScheme
    val authViewModel : AuthViewModel = viewModel()
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }

    LaunchedEffect(authViewModel.isLogged){
        if(authViewModel.isLogged){
            navController.navigate(HomeScreenRoute){
                popUpTo(LoginScreenRoute){
                    inclusive = true
                }
            }
        }
    }

    Box (
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background)
    ) {
        //FONDO
        Column(
            modifier = Modifier
                .fillMaxSize()
        ){
            Box(
                modifier = Modifier

                    .fillMaxWidth()
                    .height(300.dp)
                    .clip(RoundedCornerShape(
                        bottomEnd = 30.dp,
                        bottomStart = 30.dp
                    ))
                    .background(colors.primary)

            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()

            )

        }
        //CARD
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 20.dp)
                .height(350.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(colors.surface),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Bienvenido"
            )

            OutlinedTextField(
                value = email,
                onValueChange = { email =  it},
                placeholder = { Text("Correo Electronico")},
                modifier = Modifier
                    .fillMaxWidth()
            )

            OutlinedTextField(
                value = password,
                onValueChange = { password =  it},
                placeholder = { Text("Contraseña")},
                modifier = Modifier
                    .fillMaxWidth()
            )

            Button(
                onClick = {
                if (email.isBlank() || password.isBlank())
                    return@Button

                authViewModel.login(
                     email,
                     password
                )},
                colors = ButtonDefaults.buttonColors())
            {
                Text(
                    text = "Iniciar Sesión"
                )
            }


        }
    }
}

@Preview
@Composable
fun LoginScreenPreview(){
    RecipeTheme {
        LoginScreen(navController = rememberNavController())
    }
}