
package com.treceniomarvin.pokemonapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.treceniomarvin.pokemonapp.R
import com.treceniomarvin.pokemonapp.core.PokemonUnitConverter
import com.treceniomarvin.pokemonapp.domain.PokemonDetails
import com.treceniomarvin.pokemonapp.ui.viewmodels.PokemonViewModel
import com.treceniomarvin.pokemonapp.ui.states.Status
import com.treceniomarvin.pokemonapp.ui.theme.PokeDarkBlue
import com.treceniomarvin.pokemonapp.ui.theme.getPokemonTypeBackground

/**
 * Pokemon details screen composable that displays comprehensive information about a selected Pokemon.
 * Shows the Pokemon's image, type, height, weight, and abilities in a tabbed interface.
 * Handles loading, success, and error states.
 *
 * @param viewModel The ViewModel that provides the Pokemon details data
 * @param name The name of the Pokemon to display details for
 * @param onBackClick Callback invoked when the user taps the back button
 */
@Composable
fun PokemonDetailsScreen(
    viewModel: PokemonViewModel,
    name: String,
    onBackClick: () -> Unit = {}
) {
    val detailsUiState by viewModel.detailsUiState.collectAsStateWithLifecycle()
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val tabs = listOf("About", "Stats", "Evolution", "Moves")

    LaunchedEffect(name) {
        viewModel. getPokemonDetails(name)
    }

    when(detailsUiState.status) {
        Status.INIT -> {}
        Status.LOADING -> LoadingScreen()
        Status.SUCCESS -> {
            val backgroundColor = getPokemonTypeBackground(detailsUiState.details?.type?.first().toString())
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = backgroundColor)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.pokeball_white),
                    contentDescription = "Background",
                    modifier = Modifier
                        .alpha(0.1f)
                        .size(220.dp)
                        .align(Alignment.TopEnd)
                        .offset(x = 60.dp)
                )
                Column {
                    TopAppBar(onBackClick)
                    PokemonHeader(detailsUiState.details)
                    Spacer(modifier = Modifier.height(20.dp))
                    Box {
                        Card(
                            modifier = Modifier
                                .padding(top = 150.dp)
                                .fillMaxSize(),
                            shape = RoundedCornerShape(topStart = 22.dp, topEnd = 22.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.White
                            ),
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(16.dp)
                            ) {
                                TabRow(
                                    selectedTabIndex = selectedTabIndex,
                                    containerColor = Color.White,
                                    contentColor = Color.Black,
                                    indicator = { tabPositions ->
                                        TabRowDefaults.SecondaryIndicator(
                                            Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                                            color = Color.Black,
                                        )
                                    }
                                ) {
                                    tabs.forEachIndexed { index, title ->
                                        Tab(
                                            selected = selectedTabIndex == index,
                                            onClick = { selectedTabIndex = index },
                                            text = { Text(text = title) },
                                            selectedContentColor = PokeDarkBlue,
                                            unselectedContentColor = Color.Gray,
                                        )
                                    }
                                }
                                Spacer(modifier = Modifier.height(16.dp))
                                when (selectedTabIndex) {
                                    0 -> AboutSection(detailsUiState.details)
                                    1 -> EmptySection()
                                    2 -> EmptySection()
                                    3 -> EmptySection()
                                }
                            }
                        }

                        AsyncImage(
                            model = detailsUiState.details?.imageUrl,
                            contentDescription = "Pokemon Image",
                            modifier = Modifier
                                .align(Alignment.TopCenter)
                                .size(250.dp)
                                .offset(y = (-70).dp)
                        )
                    }
                }
            }
        }
        Status.ERROR -> {
            Text(text = "Error encountered: ${detailsUiState.error}")
        }
    }

}

/**
 * Top app bar with a back button for the details screen.
 *
 * @param onBackClick Callback invoked when the back button is tapped
 */
@Composable
private fun TopAppBar(onBackClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_arrow_back),
            contentDescription = "Back",
            tint = Color.White,
            modifier = Modifier.clickable { onBackClick() }
        )
    }
}

/**
 * Header section displaying the Pokemon's name and type chips.
 * The name is capitalized for better presentation.
 *
 * @param details The Pokemon details to display, or null if not loaded
 */
@Composable
fun PokemonHeader(
    details: PokemonDetails? = null
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = details?.name?.capitalize(Locale.current) ?: "Pokemon",
                color = Color.White,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row {
            details?.type?.forEach { type ->
                TypeChip(text = type)
                Spacer(modifier = Modifier.size(8.dp))
            }
        }
    }
}

/**
 * Displays a Pokemon type as a rounded chip with a semi-transparent white background.
 *
 * @param text The type name to display (e.g., "fire", "water", "grass")
 */
@Composable
fun TypeChip(text: String) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White.copy(alpha = 0.3f))
            .padding(horizontal = 12.dp, vertical = 4.dp),
    ) {
        Text(text = text, color = Color.White)
    }
}

/**
 * About tab section displaying the Pokemon's physical characteristics and abilities.
 * Shows formatted height, weight, and a comma-separated list of abilities.
 *
 * @param details The Pokemon details to display, or null if not loaded
 */
@Composable
fun AboutSection(
    details: PokemonDetails? = null
) {
    val height = PokemonUnitConverter.formatHeight(details?.height ?: 0)
    val weight = PokemonUnitConverter.formatWeight(details?.weight ?: 0)
    Column {
        PokemonInfoRow("Height", height)
        Spacer(modifier = Modifier.height(16.dp))
        PokemonInfoRow("Weight", weight)
        Spacer(modifier = Modifier.height(16.dp))
        PokemonInfoRow("Abilities", details?.abilities?.joinToString(", "))
        Spacer(modifier = Modifier.height(24.dp))
    }
}

/**
 * Placeholder section for tabs that have not been implemented yet.
 * Displays a "Coming soon" message centered in the available space.
 */
@Composable
fun EmptySection() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Coming soon",
            color = Color.Gray,
        )
    }
}


/**
 * Displays a labeled information row with the label in gray and value in black.
 * Used in the About section for consistent formatting.
 *
 * @param label The label text (e.g., "Height", "Weight", "Abilities")
 * @param value The value to display, or null if not available
 */
@Composable
fun PokemonInfoRow(label: String, value: String?) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            color = Color.Gray,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = value ?: "",
            color = Color.Black,
            modifier = Modifier.weight(2f)
        )
    }
}