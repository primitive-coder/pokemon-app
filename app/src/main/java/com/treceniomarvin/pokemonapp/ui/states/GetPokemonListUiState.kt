package com.treceniomarvin.pokemonapp.ui.states

import com.treceniomarvin.pokemonapp.domain.Pokemon

/**
 * UI state for the Pokemon list screen.
 * Tracks the list of Pokemon, loading status, and any error messages.
 *
 * @property pokemonList The current list of Pokemon to display
 * @property status The current loading state of the operation
 * @property error Error message if the operation failed, null otherwise
 */
data class GetPokemonListUiState(
    val pokemonList: List<Pokemon> = emptyList(),
    val status: Status = Status.INIT,
    val error: String? = null,
)

