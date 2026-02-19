package com.treceniomarvin.pokemonapp.ui.theme

import androidx.compose.ui.graphics.Color

val GrassBg = Color(0xFF7ED6B6)
val FireBg = Color(0xFFFF8A80)
val WaterBg = Color(0xFF81C9F9)
val ElectricBg = Color(0xFFFFE082)
val NormalBg = Color(0xFFBDBDBD)
val IceBg = Color(0xFFB3E5FC)
val FightingBg = Color(0xFFFFAB91)
val PoisonBg = Color(0xFFCE93D8)
val GroundBg = Color(0xFFD7CCC8)
val FlyingBg = Color(0xFFBBDEFB)
val PsychicBg = Color(0xFFF8BBD0)
val BugBg = Color(0xFFC5E1A5)
val RockBg = Color(0xFFE6EE9C)
val GhostBg = Color(0xFFB39DDB)
val DragonBg = Color(0xFF9FA8DA)
val DarkBg = Color(0xFFB0BEC5)
val SteelBg = Color(0xFFCFD8DC)
val FairyBg = Color(0xFFFFCDD2)

//Light Theme
val PokeDarkBlue = Color(0xFF1D2C5E)
val PokeDarkYellow = Color(0xFFC7A008)


//Dark Theme
val PokeLightBlue = Color(0xFF3466AF)
val PokeLightYellow = Color(0xFFFFCB05)

fun getPokemonTypeBackground(primaryType: String): Color {
    return when (primaryType.lowercase()) {
        "grass"    -> GrassBg
        "fire"     -> FireBg
        "water"    -> WaterBg
        "electric" -> ElectricBg
        "normal"   -> NormalBg
        "ice"      -> IceBg
        "fighting" -> FightingBg
        "poison"   -> PoisonBg
        "ground"   -> GroundBg
        "flying"   -> FlyingBg
        "psychic"  -> PsychicBg
        "bug"      -> BugBg
        "rock"     -> RockBg
        "ghost"    -> GhostBg
        "dragon"   -> DragonBg
        "dark"     -> DarkBg
        "steel"    -> SteelBg
        "fairy"    -> FairyBg
        else       -> Color.LightGray
    }
}