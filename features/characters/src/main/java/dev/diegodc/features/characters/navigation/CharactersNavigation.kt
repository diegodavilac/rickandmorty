package dev.diegodc.features.characters.navigation

import android.net.Uri
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import dev.diegodc.features.characters.characterDetail.CharacterDetailScreen
import dev.diegodc.features.characters.filters.FiltersScreen
import dev.diegodc.features.characters.root.CharactersScreen

const val charactersNavigationRoute = "characters"
const val charactersArgId = "characterId"
const val charactersFilterNavigationRoute = "filters"


fun NavController.navigateToCharacters(navOptions: NavOptions? = null) {
    this.navigate(charactersNavigationRoute, navOptions)
}

fun NavController.navigateToFilters(navOptions: NavOptions? = null) {
    this.navigate(charactersFilterNavigationRoute, navOptions)
}

fun NavController.navigateToCharacterDetail(characterId: String) {
    val encodedId = Uri.encode(characterId)
    this.navigate("$charactersNavigationRoute/$encodedId")
}

fun NavGraphBuilder.charactersScreens(
    onCharacterClick: (Int) -> Unit,
    onBackClick: () -> Unit,
    onFiltering: () -> Unit
) {
    composable(route = charactersNavigationRoute) {
        CharactersScreen(onCharacterClicked = onCharacterClick)
    }
    composable(
        route = "$charactersNavigationRoute/{$charactersArgId}",
        arguments = listOf(
            navArgument(charactersArgId) { type = NavType.StringType },
        ),
    ) {
        CharacterDetailScreen(onBackClick = onBackClick)
    }
    composable(route = charactersFilterNavigationRoute) {
        FiltersScreen(
            onBackClick = onBackClick, onFiltering = onFiltering
        )
    }
}
