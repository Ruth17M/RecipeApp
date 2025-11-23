package org.lasalle.recipeapp.ui.screeens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil3.compose.AsyncImage
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.lasalle.recipeapp.models.RecipeReview
import org.lasalle.recipeapp.ui.HomeScreenRoute
import org.lasalle.recipeapp.ui.LoginScreenRoute
import org.lasalle.recipeapp.ui.RecipeTheme
import org.lasalle.recipeapp.ui.screeens.home.components.LoadingOverlay
import org.lasalle.recipeapp.ui.screeens.home.components.RecipeCard
import org.lasalle.recipeapp.ui.screeens.home.components.RecipeRowItem
import org.lasalle.recipeapp.ui.viewmodels.HomeViewModel
import org.lasalle.recipeapp.utils.hideKeyboard

// 1. LazyColumn
//      item{Row} -> Header
//      item{Column} -> GenerateRecipe
//      item{LazyRow} -> Carrusel de Recetas
//      item{Text,LazyRow,Card} -> QuickIdeas
//      item{Text} Todas las recetas
//      items() todas las recetas

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController){
    val colors = MaterialTheme.colorScheme
    val container = if (isSystemInDarkTheme()) colors.surface else Color.White
    var prompt by remember {
        mutableStateOf("")
    }
    val focusManager = LocalFocusManager.current
    val vm : HomeViewModel = viewModel()
    val sheestState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false
    )
    val scope = rememberCoroutineScope()


    LazyColumn (
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background)
            .padding(15.dp)
    ) {
        // HEADER
        item {
            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column (
                    modifier = Modifier
                        .weight(1f)
                        .padding(top = 30.dp)
                ){
                    Text(
                        text = "Hola"
                    )
                    Text(
                        text = "Ruth Manríquez"
                    )
                }
                Box(
                    modifier = Modifier
                        .size(35.dp)
                        .clip(CircleShape)
                        .background(
                            colors.primary.copy(alpha = 0.2f)
                        ),
                    contentAlignment = Alignment.Center
                ){
                    Text(
                        text = "RM",
                        color = colors.primary
                    )
                }

                IconButton(
                    onClick =  {
                        vm.logout()
                        navController.navigate(LoginScreenRoute){
                            popUpTo(HomeScreenRoute){
                                inclusive = true
                            }
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.Logout,
                        contentDescription = "Cerrar sesion",
                        tint = colors.primary
                    )
                }
            }
        }

        // Generate Recipe
        item {
            Spacer(Modifier.height(15.dp))
            Text(
                text = "Crea, cocina, comparte y disfruta",
                style = MaterialTheme.typography.titleMedium,
                fontSize = 35.sp,
                lineHeight = 36.sp
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = prompt,
                onValueChange = { prompt = it },
                shape = CircleShape,
                singleLine = true,
                placeholder = { Text("Escribe tus ingredientes...")},
                trailingIcon = {
                    IconButton(
                        onClick = {
                            hideKeyboard(focusManager = focusManager)


                            vm.generatedRecipe(prompt)
                        }
                    ){
                        Icon(
                            imageVector = Icons.Default.AutoAwesome,
                            contentDescription = "Generar Receta",
                            tint = Color.White,
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(colors.primary)
                                .padding(5.dp)
                        )
                    }
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = container,
                    unfocusedContainerColor = container,
                    disabledContainerColor = container,
                    errorContainerColor = container,
                    focusedBorderColor = colors.primary,
                    unfocusedBorderColor = colors.primary.copy(alpha = 0.6f),
                    cursorColor = colors.primary,
                    focusedTextColor = colors.onSurface,
                    unfocusedTextColor = colors.onSurface,
                    focusedPlaceholderColor = colors.onSurfaceVariant,
                    unfocusedPlaceholderColor = colors.onSurfaceVariant
                )

            )

        }

        // recetas recientes
        item {
            Text(text = "Tus recetas recientes", color = colors.onSurface, style = MaterialTheme.typography.titleMedium, fontSize = 30.sp)
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(vm.recentRecipes){ recipe ->
                    RecipeCard(
                        recipe,
                        onClick = {
                            scope.launch {
                                val recipePreview = RecipeReview(
                                    title = recipe.title,
                                    category = recipe.category,
                                    minutes = recipe.minutes,
                                    ingredients = recipe.ingredients,
                                    instructions = recipe.instructions,
                                    imageUrl = recipe.imageUrl,
                                    stars = recipe.stars,
                                    prompt = ""
                                )
                                vm.showModalFromList(
                                    recipe = recipePreview
                                )
                                sheestState.partialExpand()
                            }
                        }
                    )
                }
            }
        }

        // Ideas rapidas
        item {
            val tags = listOf(
                "Rápidas (10 min)",
                "Pocas calorías",
                "Sin horno",
                "Desayunos",
            )
            Text(
                text = "Ideas Rápidas",
                color = colors.onSurface,
                fontSize = 15.sp
            )
            Spacer(Modifier.height(10.dp))
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                items(tags){ tag ->
                    Text(
                        text = tag,
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(colors.primary.copy(alpha = 0.1f))
                            .padding(horizontal = 20.dp, vertical = 10.dp),
                        color = colors.primary,
                    )
                }
                
            }
        }

        item {
            Spacer(modifier = Modifier.height(24.dp))
        }

        // Nueva Card: "¿No sabes qué cocinar hoy?"
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(24.dp))
                    .background(Color(0xFFFFEBD6))
                    .clickable {
                        vm.generatedRecipe("Sorpréndeme")
                    }
                    .padding(20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "¿No sabes qué cocinar hoy?",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            fontSize = 16.sp
                        )
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Genera una receta aleatoria",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = Color.Gray,
                            fontSize = 14.sp
                        )
                    )
                }

                // Icono (Lado Derecho)
                Icon(
                    imageVector = Icons.Default.AutoAwesome,
                    contentDescription = null,
                    tint = Color(0xFFE67C24),
                    modifier = Modifier.size(28.dp)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))
        }

        item {
            Spacer(Modifier.height(16.dp))
            Text(
                text = "Todas tus recetas",
                color = colors.onSurface,
                style = MaterialTheme.typography.titleMedium,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(16.dp))
        }


        items(vm.recipes) { recipe ->
            RecipeRowItem(
                recipe = recipe,
                onClick = {
                    scope.launch {
                        val recipePreview = RecipeReview(
                            title = recipe.title,
                            category = recipe.category,
                            minutes = recipe.minutes,
                            ingredients = recipe.ingredients,
                            instructions = recipe.instructions,
                            imageUrl = recipe.imageUrl,
                            stars = recipe.stars,
                            prompt = ""
                        )
                        vm.showModalFromList(recipePreview)
                    }
                }
            )
        }

    }

    if(vm.isLoading){
        LoadingOverlay()
    }

    if(vm.showSheet){
        ModalBottomSheet(
            onDismissRequest = {
                scope.launch {
                    sheestState.hide()
                }.invokeOnCompletion {
                    vm.showSheet = false
                }
            },
            sheetState = sheestState,
            containerColor = Color.White
        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .verticalScroll(rememberScrollState())
                    .padding(bottom = 20.dp)
            ) {
                AsyncImage(
                    model = vm.generatedRecipe?.imageUrl,
                    contentDescription = vm.generatedRecipe?.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp)
                        .clip(RoundedCornerShape(24.dp)),
                    contentScale = ContentScale.Crop
                )

                Spacer(Modifier.height(20.dp))

                Text(
                    text = vm.generatedRecipe?.title ?: "Sin título",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.Black,
                        color = Color.Black
                    )
                )

                Spacer(Modifier.height(16.dp))

                Row(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(Color(0xFFFFEBD6))
                        .padding(horizontal = 16.dp, vertical = 10.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val orangeText = Color(0xFFE67C24)
                    Icon(Icons.Default.Star, contentDescription = null, tint = orangeText, modifier = Modifier.size(18.dp))
                    Text("${vm.generatedRecipe?.stars}", color = orangeText, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(Icons.Default.Schedule, contentDescription = null, tint = orangeText, modifier = Modifier.size(18.dp))
                    Text("${vm.generatedRecipe?.minutes} min", color = orangeText, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("${vm.generatedRecipe?.category}", color = orangeText, fontWeight = FontWeight.Bold)
                }

                Spacer(Modifier.height(24.dp))

                Text(
                    text = "Ingredientes",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    color = Color.Black
                )

                Spacer(Modifier.height(12.dp))

                val ingredients = vm.generatedRecipe?.ingredients ?: listOf()
                ingredients.forEach { ingredient ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFFFEBD6))
                            .padding(horizontal = 20.dp, vertical = 14.dp)
                    ) {
                        Text(
                            text = ingredient,
                            color = Color(0xFFE67C24),
                            fontWeight = FontWeight.Medium
                        )
                    }
                }

                Spacer(Modifier.height(24.dp))

                Text(
                    text = "Preparación",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    color = Color.Black
                )

                Spacer(Modifier.height(12.dp))


                val instructions = vm.generatedRecipe?.instructions ?: listOf()


                instructions.forEachIndexed { index, instruction ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                    ) {

                        Text(
                            text = "${index + 1}.",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFFE67C24)
                            ),
                            modifier = Modifier.padding(top = 2.dp)
                        )

                        Spacer(modifier = Modifier.width(12.dp))


                        Text(
                            text = instruction,
                            style = MaterialTheme.typography.bodyLarge.copy(
                                color = Color(0xFF333333),
                                lineHeight = 24.sp
                            ),
                            modifier = Modifier.weight(1f)
                        )
                    }
                }

                Spacer(Modifier.height(16.dp))

                Button(
                    onClick = {
                        scope.launch { sheestState.hide() }
                            .invokeOnCompletion { vm.saveRecipeInDb() }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black
                    )
                ) {
                    Text("Guardar en mi recetario", fontWeight = FontWeight.Bold)
                }

                Spacer(Modifier.height(40.dp))
            }
        }
    }

    if (vm.errorMessage != null) {
        AlertDialog(
            onDismissRequest = { vm.clearError() },
            title = { Text("Hubo un problema") },
            text = { Text(text = vm.errorMessage ?: "Error desconocido") },
            confirmButton = {
                Button(onClick = { vm.clearError() }) {
                    Text("Entendido")
                }
            }
        )
    }
}

@Preview
@Composable
fun HomeScreenPreview(){
    RecipeTheme {
        HomeScreen(navController = rememberNavController())
    }
}
