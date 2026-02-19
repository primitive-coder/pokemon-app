package com.treceniomarvin.pokemonapp.domain

/**
 * Repository interface for Pokemon data operations.
 * Defines the contract for fetching Pokemon data from remote sources.
 */
interface PokemonRepository {
    /**
     * Fetches a list of Pokemon.
     *
     * @return Result containing a list of [Pokemon] on success, or an exception on failure
     */
    suspend fun getPokemonList() : Result<List<Pokemon>>

    /**
     * Fetches detailed information for a specific Pokemon.
     *
     * @param name The name of the Pokemon to fetch
     * @return Result containing [PokemonDetails] on success, or an exception on failure
     */
    suspend fun getPokemon(name: String) : Result<PokemonDetails>
}