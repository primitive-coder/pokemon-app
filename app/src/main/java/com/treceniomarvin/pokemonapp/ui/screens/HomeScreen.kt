package com.treceniomarvin.pokemonapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.treceniomarvin.pokemonapp.R
import com.treceniomarvin.pokemonapp.ui.viewmodels.PokemonViewModel
import com.treceniomarvin.pokemonapp.ui.states.Status
import com.treceniomarvin.pokemonapp.ui.theme.PokeDarkBlue

/**
 * Home screen composable that displays a grid of Pokemon cards.
 * Fetches the Pokemon list from the ViewModel and allows selection of a Pokemon.
 *
 * @param viewModel The ViewModel that provides the Pokemon list data
 * @param onPokemonSelected Callback invoked when a Pokemon card is tapped,
 *                          receives the Pokemon name as a parameter
 */
@Composable
fun HomeScreen(
    viewModel: PokemonViewModel,
    onPokemonSelected: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getPokemonList()
    }

    when (uiState.status) {
        Status.INIT -> {}
        Status.LOADING -> LoadingScreen()
        Status.SUCCESS -> {
            LazyVerticalGrid (
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.padding(16.dp)
            ) {
                items(uiState.pokemonList.size) {
                    ElevatedCard(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            onPokemonSelected(uiState.pokemonList[it].name)
                        },
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White,
                        ),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 2.dp
                        )
                    ) {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Image(
                                modifier = Modifier
                                    .size(80.dp)
                                    .padding(top = 16.dp),
                                painter = painterResource(id = R.drawable.pokeball_logo),
                                contentDescription = "Pokeball Logo"
                            )
                            Text(
                                modifier = Modifier.padding(
                                    horizontal = 22.dp,
                                    vertical = 8.dp
                                ),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = PokeDarkBlue,
                                text = uiState.pokemonList[it].name
                            )
                        }
                    }
                }
            }
        }
        Status.ERROR -> {
            Text(text = "Error encountered: ${uiState.error}")
        }
    }



}