package com.treceniomarvin.pokemonapp.data

import com.treceniomarvin.pokemonapp.data.mapper.toDomain
import com.treceniomarvin.pokemonapp.data.model.details.PokeDetailsResponseDto
import com.treceniomarvin.pokemonapp.data.model.list.PokemonResponseDto
import com.treceniomarvin.pokemonapp.domain.Pokemon
import com.treceniomarvin.pokemonapp.domain.PokemonDetails
import com.treceniomarvin.pokemonapp.domain.PokemonRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.appendPathSegments
import io.ktor.http.takeFrom
import javax.inject.Inject

/**
 * Implementation of [PokemonRepository] that fetches Pokemon data from the PokeAPI.
 *
 * @param httpClient The HTTP client used for making API requests
 */
class PokemonRepositoryImpl @Inject constructor(
     private val httpClient: HttpClient
): PokemonRepository {
    /**
     * Fetches a list of Pokemon from the PokeAPI.
     * The list is limited to 20 Pokemon.
     *
     * @return Result containing a list of [Pokemon] on success, or an exception on failure
     */
    override suspend fun getPokemonList(): Result<List<Pokemon>> {
        try {
            val response: PokemonResponseDto = httpClient.get(URL) {
                url {
                    parameters.append("limit", "20")
                }
            }.body()
            return Result.success(response.toDomain())
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

    /**
     * Fetches detailed information for a specific Pokemon from the PokeAPI.
     * The name is converted to lowercase for the API request.
     *
     * @param name The name of the Pokemon to fetch
     * @return Result containing [PokemonDetails] on success, or an exception on failure
     */
    override suspend fun getPokemon(name: String): Result<PokemonDetails> {
        try {
            val response: PokeDetailsResponseDto = httpClient.get(URL) {
                url {
                    takeFrom(URL)
                    appendPathSegments(name.lowercase())
                }
            }.body()
            return Result.success(response.toDomain())
        } catch (e: Exception) {
            return Result.failure(e)
        }

    }

    companion object {
        /** Base URL for the PokeAPI */
        private const val URL =
            "https://pokeapi.co/api/v2/pokemon"
    }
}