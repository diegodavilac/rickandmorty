package dev.diegodc.app


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import dev.diegodc.core.ui.RickAndMortyTopAppBar
import dev.diegodc.data.preferences.CommonPreferences
import dev.diegodc.features.characters.navigation.navigateToFilters
import dev.diegodc.navigation.RMNavHost
import dev.diegodc.navigation.RootDestinations
import dev.diegodc.core.ui.R as uiR
import dev.diegodc.features.characters.R as charactersR

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
internal fun RickAndMortyApp(
    appState: RickAndMortyAppState = rememberRickAndMortyAppState(),
    mainViewModel: MainViewModel = hiltViewModel()
) {
    val state by mainViewModel.state.collectAsState()

    Scaffold(
        modifier = Modifier.semantics {
            testTagsAsResourceId = true
        },
        containerColor = Color.Transparent,
        contentColor = MaterialTheme.colorScheme.onBackground,
        bottomBar = {
            if (appState.shouldShowBottomNavigation) {
                RickAndMortyBottomBar(
                    destinations = appState.topLevelDestinations,
                    onNavigateToDestination = appState::navigateToRootDestination,
                    currentDestination = appState.currentDestination,
                    modifier = Modifier.testTag("RickAndMortyBottomBar"),
                )
            }
        },
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            val destination = appState.currentTopLevelDestination
            if (destination == RootDestinations.CHARACTERS) {
                RickAndMortyTopAppBar(
                    actionIcon = uiR.drawable.ic_filter,
                    actionFillIcon = if (state.hasFilters) uiR.drawable.ic_dot else null,
                    actionIconContentDescription = null,
                    titleRes = charactersR.string.characters,
                    onActionClick = {
                        appState.navController.navigateToFilters()
                    }
                )
            }
            RMNavHost(navController = appState.navController, mainViewModel)
        }
    }
}

@Composable
private fun RickAndMortyBottomBar(
    destinations: List<RootDestinations>,
    onNavigateToDestination: (RootDestinations) -> Unit,
    currentDestination: NavDestination?,
    modifier: Modifier = Modifier,
) {
    NavigationBar(
        modifier = modifier,
        contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
        tonalElevation = 4.dp,
    ) {
        destinations.forEach { destination ->
            val selected = currentDestination.isTopLevelDestinationInHierarchy(destination)
            NavigationBarItem(
                selected = selected,
                onClick = { onNavigateToDestination(destination) },
                icon = {
                    val icon = if (selected) {
                        destination.selectedIcon
                    } else {
                        destination.unselectedIcon
                    }
                    Icon(
                        painter = painterResource(id = icon),
                        contentDescription = null,
                    )
                },
                modifier = modifier,
                label = { Text(stringResource(destination.iconTextId)) },
            )
        }
    }
}

private fun NavDestination?.isTopLevelDestinationInHierarchy(destination: RootDestinations) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false