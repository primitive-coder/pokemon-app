package com.treceniomarvin.pokemonapp.core

import java.util.Locale

object PokemonUnitConverter {

    // Height: decimeters -> formatted string
    fun formatHeight(decimeters: Int): String {
        val meters = decimeters / 10.0
        val inchesTotal = meters * 39.3701

        val feet = (inchesTotal / 12).toInt()
        val inches = inchesTotal % 12

        return String.format(
            Locale.getDefault(),
            "%d'%.1f\" (%.2f m)",
            feet,
            inches,
            meters
        )
    }

    // Weight: hectograms -> formatted string
    fun formatWeight(hectograms: Int): String {
        val kilograms = hectograms / 10.0
        val pounds = kilograms * 2.20462

        return String.format(
            Locale.getDefault(),
            "%.1f lbs (%.1f kg)",
            pounds,
            kilograms
        )
    }
}
