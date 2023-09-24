package com.hamidrezabashiri.ezcard.ui.navigation

import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hamidrezabashiri.ezcard.ui.screens.addCard.AddCardScreen
import com.hamidrezabashiri.ezcard.ui.screens.home.HomeScreen
import com.hamidrezabashiri.ezcard.ui.screens.login.LoginScreen
import com.hamidrezabashiri.ezcard.ui.screens.shareCard.ShareCardScreen
import com.hamidrezabashiri.ezcard.ui.screens.signup.SignUpScreen
import com.hamidrezabashiri.ezcard.ui.screens.welcome.WelcomeScreen


fun NavGraphBuilder.ezCardNavGraph(
    isDarkTheme: Boolean,
    upPress: () -> Unit,
    onNavigateToBottomBarRoute: (String) -> Unit,
    onNavigateToSubScreen: (String, NavBackStackEntry) -> Unit,
    onNavigateAndPoppingBackStack: (String, NavBackStackEntry) -> Unit,
) {
    composable(route = MainDestinations.WELCOME_ROUTE) {
        WelcomeScreen(
            onLogin = { onNavigateAndPoppingBackStack(MainDestinations.SIGNUP_ROUTE, it) },
            isDarkTheme = isDarkTheme
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
        AddCardScreen()

    }
    composable(route = MainDestinations.HOME_ROUTE) {
        HomeScreen(
            isDarkTheme = isDarkTheme,
            navigateToAddScreen = { onNavigateToSubScreen(MainDestinations.ADD_CARD_ROUTE, it) },
            navigateToShareScreen = { onNavigateToSubScreen(MainDestinations.SHARE_CARD_ROUTE, it) }

        )

    }
    composable(route = MainDestinations.SHARE_CARD_ROUTE) {

        ShareCardScreen()

    }
    composable(route = MainDestinations.WALLET_ROUTE) {

    }

}