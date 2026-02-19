package com.treceniomarvin.pokemonapp.domain

/**
 * Domain model representing detailed information about a Pokemon.
 * Contains comprehensive data for the details screen.
 *
 * @property id The unique identifier of the Pokemon
 * @property name The name of the Pokemon
 * @property type List of Pokemon types (e.g., fire, water, grass)
 * @property height The height of the Pokemon in decimeters
 * @property weight The weight of the Pokemon in hectograms
 * @property imageUrl URL to the Pokemon's official artwork image
 * @property abilities List of the Pokemon's abilities
 */
data class PokemonDetails(
    val id: Int,
    val name: String,
    val type: List<String>,
    val height: Int,
    val weight: Int,
    val imageUrl: String,
    val abilities: List<String>,
)
