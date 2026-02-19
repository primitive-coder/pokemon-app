package com.treceniomarvin.pokemonapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.treceniomarvin.pokemonapp.R
import com.treceniomarvin.pokemonapp.ui.states.Status
import com.treceniomarvin.pokemonapp.ui.theme.PokeDarkBlue
import com.treceniomarvin.pokemonapp.ui.theme.PokeDarkYellow
import com.treceniomarvin.pokemonapp.ui.viewmodels.AuthViewModel

/**
 * Login screen composable that displays the app logo, username/password input fields,
 * and a login button. This is the entry point of the application.
 *
 * @param onLoginSuccess Callback invoked when the user taps the login button
 */
@Composable
fun LoginScreen(
    viewModel: AuthViewModel,
    onLoginSuccess: () -> Unit = {}
) {
    // UI for the login screen
    val username = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val authUiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.pokemon_logo),
            contentDescription = "Logo",
            modifier = Modifier.size(220.dp),
        )
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .background(Color.White),
            verticalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                value = username.value,
                onValueChange = { username.value = it },
                label = { Text("Username") },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = PokeDarkYellow,
                    unfocusedBorderColor = Color.Gray,
                    focusedTextColor = PokeDarkBlue,
                    unfocusedTextColor = Color.Gray,
                )
            )
            OutlinedTextField(
                value = password.value,
                onValueChange = { password.value = it },
                label = { Text("Password") },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = PokeDarkYellow,
                    unfocusedBorderColor = Color.Gray,
                    focusedTextColor = PokeDarkBlue,
                    unfocusedTextColor = Color.Gray,
                )
            )
            Button(
                onClick = {
                    viewModel.authUser(username.value, password.value)
                },
                modifier = Modifier.fillMaxWidth().padding(top = 40.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = PokeDarkBlue,
                    contentColor = Color.White
                )
            ) {
                Text(
                    modifier = Modifier.padding(vertical = 8.dp),
                    text = "Login"
                )
            }
            when (authUiState.status) {
                Status.INIT -> {}
                Status.LOADING -> LoadingScreen()
                Status.SUCCESS -> {
                    onLoginSuccess()
                }
                Status.ERROR -> {
                    Text(
                        modifier = Modifier.padding(vertical = 8.dp).fillMaxWidth(),
                        text = authUiState.error ?: "Error logging in",
                        textAlign = TextAlign.Center,
                        color = Color.Red
                    )
                }
            }

        }
    }
}