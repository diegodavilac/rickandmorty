package dev.diegodc.features.characters.filters.model

import dev.diegodc.core.common.utils.asEnumOrNull
import dev.diegodc.data.preferences.model.Filters
import dev.diegodc.data.preferences.model.Gender
import dev.diegodc.data.preferences.model.Species
import dev.diegodc.data.preferences.model.Status
import dev.diegodc.features.characters.filters.CharacterGender
import dev.diegodc.features.characters.filters.CharacterSpecies
import dev.diegodc.features.characters.filters.CharacterStatus
import dev.diegodc.features.characters.filters.FiltersViewState

fun FiltersViewState.toData(): Filters {
    return Filters(
        name = name,
        gender= gender?.name?.asEnumOrNull<Gender>(null),
        species = species?.name?.asEnumOrNull<Species>(null),
        status = status?.name?.asEnumOrNull<Status>(null)
    )
}

fun Filters.toUi() : FiltersViewState{
    return FiltersViewState(
        name = name?:"",
        gender= gender?.name?.asEnumOrNull<CharacterGender>(null),
        species = species?.name?.asEnumOrNull<CharacterSpecies>(null),
        status = status?.name?.asEnumOrNull<CharacterStatus>(null)
    )
}