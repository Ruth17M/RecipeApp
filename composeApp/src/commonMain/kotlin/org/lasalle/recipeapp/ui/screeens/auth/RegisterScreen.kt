package org.lasalle.recipeapp.ui.screeens.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.lasalle.recipeapp.ui.RecipeTheme
import org.lasalle.recipeapp.ui.viewmodels.AuthViewModel

@Composable
fun RegisterScreen(navController: NavController){
    val colors = MaterialTheme.colorScheme
    val authViewModel : AuthViewModel = viewModel()
    var name by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var confirmedPassword  by remember {
        mutableStateOf("")
    }
    Box (
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background)
    ){
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .clip(RoundedCornerShape(
                    bottomStart = 50.dp,
                    bottomEnd = 50.dp
                ))
                .background(colors.primary)
        )

        Column (
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth()
                .height(400.dp)
                .padding(horizontal = 20.dp)
                .shadow(10.dp, RoundedCornerShape(40.dp))
                .clip(RoundedCornerShape(40.dp))
                .background(colors.surface),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = "Crear Cuenta"
            )
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                placeholder = {
                    Text(
                        text = "Nombre"
                    )
                }
            )
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                placeholder = {
                    Text(
                        text = "Correo Electrónico"
                    )
                }
            )
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                placeholder = {
                    Text(
                        text = "Contraseña"
                    )
                }
            )
            OutlinedTextField(
                value = confirmedPassword,
                onValueChange = { confirmedPassword = it },
                placeholder = {
                    Text(
                        text = "Confirmar Contraseña"
                    )
                }
            )

            Button(
                onClick = {
                    if(name.isBlank() ||
                        email.isBlank() ||
                        password.isBlank() ||
                        confirmedPassword.isBlank()
                        )
                    {
                        println("Faltan valores por completar")
                        return@Button
                    }

                    if(password != confirmedPassword){
                        println("Las contraseñas nos son iguales")
                        return@Button
                    }

                    // EXPRESIONES REGULARES

                    authViewModel.register(
                        name = name,
                        email = email,
                        password = password
                    )
                }
            ){
                Text(
                    text = "Registrarse"
                )
            }
        }
    }
}

@Preview
@Composable
fun RegisterScreenPreview(){
    RecipeTheme {
        RegisterScreen(navController = rememberNavController())
    }
}