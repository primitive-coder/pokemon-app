package com.treceniomarvin.pokemonapp.data.model.details

import com.google.gson.annotations.SerializedName

data class PokeDetailsResponseDto(
    val abilities: List<AbilityDto> = listOf(),
    val height: Int = 0,
    val id: Int = 0,
    val sprites: SpritesDto = SpritesDto(),
    val types: List<TypeDto> = listOf(),
    val weight: Int = 0,
    val name: String = ""
)

data class AbilityDto(
    val ability: CoreAbilityDto = CoreAbilityDto()
)

data class CoreAbilityDto(
    val name: String = ""
)

data class CoreTypeDto(
    val name: String = "",
)

data class HomeDto(
    @SerializedName("front_default")
    val frontDefault: String = ""
)

data class OtherDto(
    val home: HomeDto = HomeDto()
)

data class SpeciesDto(
    val name: String = "",
    val url: String = ""
)

data class SpritesDto(
    val other: OtherDto = OtherDto()
)

data class TypeDto(
    val type: CoreTypeDto = CoreTypeDto()
)