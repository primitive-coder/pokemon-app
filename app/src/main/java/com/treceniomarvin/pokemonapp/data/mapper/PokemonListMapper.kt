package com.treceniomarvin.pokemonapp.data.mapper

import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import com.treceniomarvin.pokemonapp.data.model.list.PokemonResponseDto
import com.treceniomarvin.pokemonapp.domain.Pokemon

/**
 * Converts a [PokemonResponseDto] to a list of domain [Pokemon] objects.
 * Each Pokemon name is capitalized for better presentation.
 *
 * @return A list of [Pokemon] domain models
 */
fun PokemonResponseDto.toDomain(): List<Pokemon> {
    return this.results.map {
        Pokemon(
            name = it.name.capitalize(Locale.current)
        )
    }
}


