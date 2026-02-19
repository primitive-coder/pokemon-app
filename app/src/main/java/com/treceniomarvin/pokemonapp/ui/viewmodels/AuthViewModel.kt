package com.treceniomarvin.pokemonapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.treceniomarvin.pokemonapp.ui.states.AuthUiState
import com.treceniomarvin.pokemonapp.ui.states.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    fun authUser(username: String, password: String) {
        _uiState.update { it.copy(status = Status.LOADING) }
        viewModelScope.launch {
            if (username.isNotEmpty() && password.isNotEmpty()) {
                _uiState.update { it.copy(status = Status.SUCCESS) }
            } else {
                _uiState.update { it.copy(
                    error = CREDENTIAL_REQUIRED,
                    status = Status.ERROR
                ) }
            }
        }
    }
    companion object {
        const val CREDENTIAL_REQUIRED = "Username and password required"
    }
}