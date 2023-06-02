package dev.diegodc.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import dev.diegodc.features.characters.R as characterR
import dev.diegodc.features.episodes.R as episodesR
import dev.diegodc.features.locations.R as locationR

enum class RootDestinations(
    @DrawableRes val selectedIcon: Int,
    @DrawableRes val unselectedIcon: Int,
    @StringRes val iconTextId: Int,
    @StringRes val titleTextId: Int,
) {
    CHARACTERS(
        selectedIcon = characterR.drawable.ic_person,
        unselectedIcon = characterR.drawable.ic_person,
        iconTextId = characterR.string.characters,
        titleTextId = characterR.string.characters,
    ),
    EPISODES(
        selectedIcon = episodesR.drawable.ic_library,
        unselectedIcon = episodesR.drawable.ic_library,
        iconTextId = episodesR.string.episodes,
        titleTextId = episodesR.string.episodes,
    ),
    LOCATIONS(
        selectedIcon = locationR.drawable.ic_location,
        unselectedIcon = locationR.drawable.ic_location,
        iconTextId = locationR.string.locations,
        titleTextId = locationR.string.locations,
    ),
}