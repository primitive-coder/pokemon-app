package com.treceniomarvin.pokemonapp.ui.states

/**
 * Represents the various states of an asynchronous operation.
 * Used to track loading and result states across the UI.
 */
enum class Status {
    /** Initial state before any operation has started */
    INIT,
    /** Operation is currently in progress */
    LOADING,
    /** Operation completed successfully */
    SUCCESS,
    /** Operation failed with an error */
    ERROR
}
