package org.lasalle.recipeapp.data.services

import com.russhwolf.settings.Settings

// Singleton

object Preferences{
    private val settings = Settings()

    fun saveUserId(userId: Int){
        settings.putInt("userId", userId)
    }

    fun saveIsLogged(isLogged: Boolean){
        settings.putBoolean("isLogged", isLogged)
    }

    fun getUserId() : Int{
        return settings.getInt("userId", 0)
    }

    fun getIsLogged() : Boolean{
        return settings.getBoolean("isLogged", false)
    }

    fun clearPreferences(){
        settings.clear()
    }
}