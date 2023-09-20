package com.hamidrezabashiri.ezcard.ui.navigation

import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hamidrezabashiri.ezcard.ui.screens.login.LoginScreen
import com.hamidrezabashiri.ezcard.ui.screens.signup.SignUpScreen
import com.hamidrezabashiri.ezcard.ui.screens.welcome.WelcomeScreen


fun NavGraphBuilder.ezCardNavGraph(
    upPress: () -> Unit,
    onNavigateToBottomBarRoute: (String) -> Unit,
    onNavigateToSubScreen: (String, NavBackStackEntry) -> Unit,
    onNavigateAndPoppingBackStack: (String, NavBackStackEntry) -> Unit
) {
    composable(route = MainDestinations.WELCOME_ROUTE) {
        WelcomeScreen(
            onLogin = { onNavigateToSubScreen(MainDestinations.SIGNUP_ROUTE, it) }
        )
    }

    composable(route = MainDestinations.SIGNUP_ROUTE) {
        SignUpScreen(onSignUpSuccess = {
            onNavigateAndPoppingBackStack(
                MainDestinations.HOME_ROUTE,
                it
            )
        })
    }
    composable(route = MainDestinations.LOGIN_ROUTE) {
        LoginScreen(
            onLoginSuccess = {
                onNavigateAndPoppingBackStack(
                    MainDestinations.HOME_ROUTE,
                    it
                )
            }
        )

    }
    composable(route = MainDestinations.SETTINGS_ROUTE) {

    }
    composable(route = MainDestinations.ADD_CARD_ROUTE) {

    }
    composable(route = MainDestinations.HOME_ROUTE) {

    }
    composable(route = MainDestinations.SHARE_CARD_ROUTE) {

    }
    composable(route = MainDestinations.WALLET_ROUTE) {

    }

}