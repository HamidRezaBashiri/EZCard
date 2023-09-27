package com.hamidrezabashiri.ezcard.ui.navigation

import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.hamidrezabashiri.ezcard.ui.screens.addCard.AddCardScreen
import com.hamidrezabashiri.ezcard.ui.screens.confirmDelete.ConfirmDeleteScreen
import com.hamidrezabashiri.ezcard.ui.screens.home.HomeScreen
import com.hamidrezabashiri.ezcard.ui.screens.login.LoginScreen
import com.hamidrezabashiri.ezcard.ui.screens.shareCard.ShareCardScreen
import com.hamidrezabashiri.ezcard.ui.screens.signup.SignUpScreen
import com.hamidrezabashiri.ezcard.ui.screens.wallet.WalletScreen
import com.hamidrezabashiri.ezcard.ui.screens.welcome.WelcomeScreen


fun NavGraphBuilder.ezCardNavGraph(
    isDarkTheme: Boolean,
    upPress: () -> Unit,
    onNavigateWithParams: (Int, NavBackStackEntry, String) -> Unit,
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
            },onFirstLaunch = { onNavigateAndPoppingBackStack(MainDestinations.WELCOME_ROUTE, it) }

        )

    }
    composable(route = MainDestinations.SETTINGS_ROUTE) {

    }
    composable(route = MainDestinations.ADD_CARD_ROUTE) {
        AddCardScreen(navigateUp = { upPress() })

    }
    composable(route = MainDestinations.HOME_ROUTE) {
        HomeScreen(
            isDarkTheme = isDarkTheme,
            navigateToAddScreen = { onNavigateToSubScreen(MainDestinations.ADD_CARD_ROUTE, it) },
            navigateWithParam = onNavigateWithParams,
            navBackStackEntry = it

        )

    }
    composable(
        route = "${MainDestinations.SHARE_CARD_ROUTE}/{param}",
        arguments = listOf(navArgument("param") { type = NavType.IntType })

    ) {
        val param = it.arguments?.getInt("param")

        ShareCardScreen(cardId = param, upPress = upPress)

    }
    composable(
        route = "${MainDestinations.CONFIRM_DELETE}/{param}",
        arguments = listOf(navArgument("param") { type = NavType.IntType })
    ) {
        val param = it.arguments?.getInt("param")

        ConfirmDeleteScreen(upPress = upPress, cardId = param)

    }
    composable(route = MainDestinations.WALLET_ROUTE) {

        WalletScreen(
            navigateToAddScreen = {
                onNavigateToSubScreen(
                    MainDestinations.ADD_CARD_ROUTE,
                    it
                )
            }, navigateToDeleteScreen = onNavigateWithParams, navBackStackEntry = it

        )
    }

}