package com.treceniomarvin.pokemonapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.treceniomarvin.pokemonapp.data.PokemonRepositoryImpl
import com.treceniomarvin.pokemonapp.ui.states.GetPokemonDetailsUiState
import com.treceniomarvin.pokemonapp.ui.states.GetPokemonListUiState
import com.treceniomarvin.pokemonapp.ui.states.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel responsible for managing Pokemon data and UI state.
 * Handles fetching Pokemon lists and details from the repository.
 *
 * @param repositoryImpl The repository implementation for Pokemon data operations
 */
@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val repositoryImpl: PokemonRepositoryImpl
) : ViewModel() {
    private val _uiState = MutableStateFlow(GetPokemonListUiState())
    val uiState: StateFlow<GetPokemonListUiState> = _uiState.asStateFlow()

    private val _detailsUiState = MutableStateFlow(GetPokemonDetailsUiState())
    val detailsUiState: StateFlow<GetPokemonDetailsUiState> = _detailsUiState.asStateFlow()

    /**
     * Fetches the list of Pokemon from the repository.
     * Updates the UI state with loading, success, or error status.
     * Results are limited to 20 Pokemon as configured in the repository.
     */
    fun getPokemonList() {
        viewModelScope.launch {
            _uiState.update { it.copy(status = Status.LOADING) }
            repositoryImpl.getPokemonList()
                .onSuccess { result ->
                    _uiState.update {
                        it.copy(pokemonList = result, status = Status.SUCCESS)
                    }
                }
                .onFailure { err ->
                    _uiState.update { it.copy(error = err.message, status = Status.ERROR) }
                }

        }
    }

    /**
     * Fetches detailed information for a specific Pokemon by name.
     * Updates the details UI state with loading, success, or error status.
     *
     * @param name The name of the Pokemon to fetch details for
     */
    fun getPokemonDetails(name: String) {
        viewModelScope.launch {
            _detailsUiState.update { it.copy(status = Status.LOADING) }
            repositoryImpl.getPokemon(name)
                .onSuccess { result ->
                    _detailsUiState.update {
                        it.copy(details = result, status = Status.SUCCESS)
                    }
                }
                .onFailure { err ->
                    _detailsUiState.update { it.copy(error = err.message, status = Status.ERROR) }
                }
        }
    }
}