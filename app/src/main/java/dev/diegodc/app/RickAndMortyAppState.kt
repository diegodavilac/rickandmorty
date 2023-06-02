package dev.diegodc.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import dev.diegodc.data.preferences.CommonPreferences
import dev.diegodc.data.preferences.model.Filters
import dev.diegodc.features.characters.navigation.charactersFilterNavigationRoute
import dev.diegodc.features.characters.navigation.charactersNavigationRoute
import dev.diegodc.features.characters.navigation.navigateToCharacters
import dev.diegodc.features.episodes.navigation.episodesNavigationRoute
import dev.diegodc.features.episodes.navigation.navigateToEpisodes
import dev.diegodc.features.locations.navigation.locationsNavigationRoute
import dev.diegodc.features.locations.navigation.navigateToLocations
import dev.diegodc.navigation.RootDestinations
import javax.inject.Inject

@Composable
fun rememberRickAndMortyAppState(
    navController: NavHostController = rememberNavController(),
): RickAndMortyAppState {
    return remember(navController) {
        RickAndMortyAppState(navController)
    }
}

class RickAndMortyAppState @Inject constructor(
    val navController: NavHostController
) {

    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val currentTopLevelDestination: RootDestinations?
        @Composable get() = when (currentDestination?.route) {
            charactersNavigationRoute -> RootDestinations.CHARACTERS
            episodesNavigationRoute -> RootDestinations.EPISODES
            locationsNavigationRoute -> RootDestinations.LOCATIONS
            else -> null
        }

    val topLevelDestinations: List<RootDestinations> = RootDestinations.values().asList()

    val shouldShowBottomNavigation : Boolean
        @Composable get() {
            return currentDestination?.route != charactersFilterNavigationRoute
        }

    fun navigateToRootDestination(rootDestinations: RootDestinations) {
        val topLevelNavOptions = navOptions {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            // Avoid multiple copies of the same destination when
            // reselecting the same item
            launchSingleTop = true
            // Restore state when reselecting a previously selected item
            restoreState = true
        }

        when (rootDestinations) {
            RootDestinations.CHARACTERS -> navController.navigateToCharacters(topLevelNavOptions)
            RootDestinations.EPISODES -> navController.navigateToEpisodes(topLevelNavOptions)
            RootDestinations.LOCATIONS -> navController.navigateToLocations(topLevelNavOptions)
        }
    }

}