package com.hamidrezabashiri.ezcard.ui.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow


object MainDestinations {
    const val HOME_ROUTE = "home"
    const val WALLET_ROUTE = "wallet"
    const val SETTINGS_ROUTE = "settings"
    const val LOGIN_ROUTE = "login"
    const val SIGNUP_ROUTE = "signup"
    const val SHARE_CARD_ROUTE = "shareCard"
    const val ADD_CARD_ROUTE = "addCard"
    const val EDIT_CARD_ROUTE = "editCard"
    const val WELCOME_ROUTE = "welcome_screen"
    const val CONFIRM_DELETE_ROUTE="confirm_delete"
    const val CHANGE_PASSWORD_ROUTE="change_password"
    const val ABOUT_US_ROUTE="about_us"
}

/**
 * Remembers and creates an instance of [EzCardNavController]
 */
@Composable
fun rememberEzCardNavController(
    navController: NavHostController = rememberNavController(),
): EzCardNavController = remember(navController) {
    EzCardNavController(navController)
}

/**
 * Responsible for holding UI Navigation logic.
 */
@Stable
class EzCardNavController(
    val navController: NavHostController,
) {

    // ----------------------------------------------------------
    // Navigation state source of truth
    // ----------------------------------------------------------

    val currentRoute: String?
        get() = navController.currentDestination?.route


    private val _currentRouteFlow = MutableStateFlow<String?>(null)
    val currentRouteFlow: Flow<String?> = _currentRouteFlow

    init {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            _currentRouteFlow.value = destination.route
        }
    }

    fun upPress() {
        navController.navigateUp()
    }

    fun navigateToBottomBarRoute(route: String) {

        if (route != currentRoute) {

            navController.navigate(route) {

                popUpTo(MainDestinations.HOME_ROUTE) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        }
    }

    fun navigateToSubScreen(route: String, from: NavBackStackEntry) {
        if (from.lifecycleIsResumed()) {
            navController.navigate(route)
        }
    }

    fun navigateAndPopAllBackStackEntries(route: String, from: NavBackStackEntry) {
        navController.navigate(route) {
            Log.i("TAG", "navigateAndPopAllBackStackEntries: " + from.destination.route)
            from.destination.route?.let {
                popUpTo(it) {
                    inclusive = true
                }
            }
        }
    }

    fun navigateWithParam(param: Int, from: NavBackStackEntry, route: String) {
        // In order to discard duplicated navigation events, we check the Lifecycle
        if (from.lifecycleIsResumed()) {
            navController.navigate("$route/$param")
        }
    }
}

/**
 * If the lifecycle is not resumed it means this NavBackStackEntry already processed a nav event.
 *
 * This is used to de-duplicate navigation events.
 */
private fun NavBackStackEntry.lifecycleIsResumed() =
    this.lifecycle.currentState == Lifecycle.State.RESUMED

private val NavGraph.startDestination: NavDestination?
    get() = findNode(startDestinationId)

/**
 * Copied from similar function in NavigationUI.kt
 *
 * https://cs.android.com/androidx/platform/frameworks/support/+/androidx-main:navigation/navigation-ui/src/main/java/androidx/navigation/ui/NavigationUI.kt
 */
private tailrec fun findStartDestination(graph: NavDestination): NavDestination {
    return if (graph is NavGraph) findStartDestination(graph.startDestination!!) else graph
}