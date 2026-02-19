package com.treceniomarvin.pokemonapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.treceniomarvin.pokemonapp.R

/**
 * Loading screen composable displayed while data is being fetched.
 * Shows a centered circular progress indicator with a Pokeball logo in the background.
 */
@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.pokeball_logo),
            contentDescription = "Pokeball Logo",
            modifier = Modifier.align(Alignment.Center).size(60.dp).alpha(0.4f)
        )
        CircularProgressIndicator(
            modifier = Modifier.size(80.dp)
                .align(Alignment.Center),
        )
    }
}