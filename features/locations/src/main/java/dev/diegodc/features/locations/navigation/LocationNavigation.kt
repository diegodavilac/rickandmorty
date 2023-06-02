package dev.diegodc.features.locations.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import dev.diegodc.features.locations.root.LocationsScreen

const val locationsNavigationRoute = "locations"

fun NavController.navigateToLocations(navOptions: NavOptions? = null) {
    this.navigate(locationsNavigationRoute, navOptions)
}

fun NavGraphBuilder.locationsScreens() {
    composable(route = locationsNavigationRoute) {
        LocationsScreen()
    }
}