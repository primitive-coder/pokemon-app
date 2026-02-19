package com.treceniomarvin.pokemonapp.data.mapper

import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import com.treceniomarvin.pokemonapp.data.model.details.PokeDetailsResponseDto
import com.treceniomarvin.pokemonapp.domain.PokemonDetails

/**
 * Converts a [PokeDetailsResponseDto] to a domain [PokemonDetails] object.
 * Transforms the DTO response from the API into a domain model suitable for UI presentation.
 * Pokemon names and ability names are capitalized for better presentation.
 *
 * @return A [PokemonDetails] domain model containing the Pokemon's detailed information
 */
fun PokeDetailsResponseDto.toDomain(): PokemonDetails {
    return PokemonDetails(
        id = id,
        name = name,
        height = height,
        weight = weight,
        type = types.map { it.type.name },
        imageUrl = sprites.other.home.frontDefault,
        abilities = abilities.map { it.ability.name.capitalize(Locale.current) }
    )
}


