package dev.diegodc.data.preferences.model

import kotlinx.serialization.Serializable

@Serializable
data class Filters(
    val name: String? = null,
    val status: Status? = null,
    val gender: Gender? = null,
    val species: Species? = null
)

enum class Status {
    alive, dead, unknown
}

enum class Gender {
    female, male, genderless, unknown
}

enum class Species {
    human, alien, unknown
}
