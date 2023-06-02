package dev.diegodc.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import dev.diegodc.app.MainViewModel
import dev.diegodc.features.characters.navigation.charactersNavigationRoute
import dev.diegodc.features.characters.navigation.charactersScreens
import dev.diegodc.features.characters.navigation.navigateToCharacterDetail
import dev.diegodc.features.characters.navigation.navigateToCharacters
import dev.diegodc.features.episodes.navigation.episodesScreens
import dev.diegodc.features.locations.navigation.locationsScreens

@Composable
fun RMNavHost(
    navController: NavHostController,
    mainViewModel: MainViewModel,
    modifier: Modifier = Modifier,
    startDestination: String = charactersNavigationRoute,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        charactersScreens(
            onCharacterClick = {
                navController.navigateToCharacterDetail(it.toString())
            },
            onBackClick = {
                navController.popBackStack()
                mainViewModel.refresh()
            },
            onFiltering = {
                mainViewModel.refresh()
                navController.navigateToCharacters()
            }
        )
        episodesScreens()
        locationsScreens()
    }
}
