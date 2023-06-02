package dev.diegodc.features.episodes.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import dev.diegodc.features.episodes.root.EpisodesScreen

const val episodesNavigationRoute = "episodes"

fun NavController.navigateToEpisodes(navOptions: NavOptions? = null) {
    this.navigate(episodesNavigationRoute, navOptions)
}

fun NavGraphBuilder.episodesScreens() {
    composable(route = episodesNavigationRoute) {
        EpisodesScreen()
    }
}