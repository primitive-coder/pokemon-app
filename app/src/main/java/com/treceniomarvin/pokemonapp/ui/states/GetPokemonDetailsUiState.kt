package com.treceniomarvin.pokemonapp.ui.states

import com.treceniomarvin.pokemonapp.domain.PokemonDetails

/**
 * UI state for the Pokemon details screen.
 * Tracks the detailed information of a selected Pokemon, loading status, and any error messages.
 *
 * @property details The detailed information of the selected Pokemon, null if not loaded
 * @property status The current loading state of the operation
 * @property error Error message if the operation failed, null otherwise
 */
data class GetPokemonDetailsUiState(
    val details: PokemonDetails? = null,
    val status: Status = Status.INIT,
    val error: String? = null,
)