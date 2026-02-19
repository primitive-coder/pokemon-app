package com.treceniomarvin.pokemonapp.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.treceniomarvin.pokemonapp.ui.viewmodels.AuthViewModel
import com.treceniomarvin.pokemonapp.ui.viewmodels.PokemonViewModel

/**
 * Main screen composable that sets up the navigation graph for the application.
 * Defines the navigation routes: login, home, and details screens.
 */
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val viewModel = hiltViewModel<PokemonViewModel>()
    val authViewModel = hiltViewModel<AuthViewModel>()

    NavHost(
        modifier = Modifier.fillMaxSize().systemBarsPadding(),
        navController = navController,
        startDestination = "login_screen",
    ) {
        composable("login_screen") {
            LoginScreen(authViewModel) {
                navController.navigate("home_screen")
            }
        }

        composable("home_screen") {
            HomeScreen(viewModel) { name ->
                navController.navigate("details_screen/$name")
            }
        }

        composable(
            route = "details_screen/{name}",
            arguments = listOf(
                navArgument("name") { type = NavType.StringType }
            )
        ) { backStackEntry ->

            val name = backStackEntry.arguments?.getString("name") ?: ""

            PokemonDetailsScreen(viewModel, name) {
                navController.navigate("home_screen")
            }
        }

    }

}