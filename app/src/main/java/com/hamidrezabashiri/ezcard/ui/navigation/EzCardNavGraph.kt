package com.hamidrezabashiri.ezcard.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hamidrezabashiri.ezcard.ui.screens.login.LoginScreen
import com.hamidrezabashiri.ezcard.ui.screens.login.LoginViewModel
import com.hamidrezabashiri.ezcard.ui.screens.signup.SignUpScreen
import com.hamidrezabashiri.ezcard.ui.screens.signup.SignUpViewModel

object MainDestinations {
    const val HOME_ROUTE = "home"
    const val WALLET_ROUTE = "wallet"
    const val SETTINGS_ROUTE = "settings"
    const val LOGIN_ROUTE = "login"
    const val SIGNUP_ROUTE = "signup"
    const val SHARE_CARD_ROUTE = "shareCard"
    const val ADD_CARD_ROUTE = "addCard"
}

@Composable
fun EzCardNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = MainDestinations.SIGNUP_ROUTE
) {

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = MainDestinations.SIGNUP_ROUTE) {
            val signUpViewModel = hiltViewModel<SignUpViewModel>()
            SignUpScreen(signUpViewModel)
        }
        composable(route = MainDestinations.LOGIN_ROUTE) {
            val loginViewModel: LoginViewModel = hiltViewModel()
            LoginScreen(
                viewModel = loginViewModel,
                onLoginSuccess = { navController.navigate(MainDestinations.HOME_ROUTE) }
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
}