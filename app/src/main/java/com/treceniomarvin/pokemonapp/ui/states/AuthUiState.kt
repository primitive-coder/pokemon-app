package com.treceniomarvin.pokemonapp.ui.states

import com.treceniomarvin.pokemonapp.domain.User

data class AuthUiState(
    val user: User? = null,
    val status: Status = Status.INIT,
    val error: String? = null,
)