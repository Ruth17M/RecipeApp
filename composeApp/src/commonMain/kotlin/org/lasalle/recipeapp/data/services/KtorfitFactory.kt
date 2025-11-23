package org.lasalle.recipeapp.data.services

import de.jensklingenberg.ktorfit.Ktorfit
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.accept
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object KtorfitFactory{
    val base = "https://recipes.pjasoft.com/api/"

    // Clientes Http Axios, Fetch
    // NO TENEMOS CERTEZZA DE QUE LA API RESPONDA SIEMPRE IGUAL
    // QUE PASA SI LA API NO RESPONDE CON EL STATUS CODE 409
    // API TARDA EN RESPONDER ->10s

    private val httpClient = HttpClient {
        expectSuccess = false
        // PERMITE QUE LA API TARDE MÁS EN SU RESPUESTA { POR DEFECTO SOLO DA 10 S}
        install(HttpTimeout){
            requestTimeoutMillis = 40000
            connectTimeoutMillis = 40000
            socketTimeoutMillis = 40000
        }
        // XML -> SOAP
        install(ContentNegotiation){
            json(
                Json {
                    isLenient = true // PERMITE JSONS IMPERFECTOS O INCOMPLETOS
                    ignoreUnknownKeys = true // IGNORA PROPIEDADES NO MAPEADAS
                }
            )
        }
        defaultRequest {
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
        }

    }
    private val ktorfit = Ktorfit
        .Builder()
        .httpClient(httpClient)
        .baseUrl(base)
        .build()

    fun getAuthService() : AuthService{
        return ktorfit.createAuthService()
    }

    fun getRecipeService() : RecipeService{
        return ktorfit.createRecipeService()
    }
}