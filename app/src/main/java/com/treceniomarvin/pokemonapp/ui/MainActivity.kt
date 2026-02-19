package com.treceniomarvin.pokemonapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.treceniomarvin.pokemonapp.ui.screens.LoginScreen
import com.treceniomarvin.pokemonapp.ui.screens.MainScreen
import com.treceniomarvin.pokemonapp.ui.theme.PokemonAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PokemonAppTheme {
                MainScreen()
            }
        }
    }
}