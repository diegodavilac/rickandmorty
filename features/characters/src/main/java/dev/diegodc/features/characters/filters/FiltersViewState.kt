package dev.diegodc.features.characters.filters

data class FiltersViewState(
    val name: String? = null,
    val status: CharacterStatus? = null,
    val gender: CharacterGender? = null,
    val species: CharacterSpecies? = null
)

enum class CharacterStatus {
    alive, dead, unknown
}

enum class CharacterGender {
    female, male, genderless, unknown
}

enum class CharacterSpecies {
    human, alien, unknown
}