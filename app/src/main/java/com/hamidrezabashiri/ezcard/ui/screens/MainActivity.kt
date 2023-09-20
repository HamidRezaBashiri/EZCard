package com.hamidrezabashiri.ezcard.ui.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import com.hamidrezabashiri.ezcard.ui.MainViewModel
import com.hamidrezabashiri.ezcard.ui.navigation.MainDestinations
import com.hamidrezabashiri.ezcard.ui.navigation.ezCardNavGraph
import com.hamidrezabashiri.ezcard.ui.navigation.rememberEzCardNavController
import com.hamidrezabashiri.ezcard.ui.theme.EzCardTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val ezCardNavController = rememberEzCardNavController()

            val mainViewModel: MainViewModel = hiltViewModel()

            val isSystemInDarkTheme = isSystemInDarkTheme()
            var isDarkTheme by remember {
                mutableStateOf(isSystemInDarkTheme)
            }


            var startDestination = MainDestinations.LOGIN_ROUTE
            // Observe the Flow<Boolean> from the ViewModel
            val isFirstLogin: Flow<Boolean> = mainViewModel.isFirstLogin
            // Use collectAsState to collect the latest value and provide it to the Composable
            val isFirstLoginState by isFirstLogin.collectAsState(false)

            if (isFirstLoginState) {
//                TODO FIX SHOWING OTHER SCREEN BEFORE GETTING THE VALUE FROM VIEW MODEL
                startDestination = MainDestinations.WELCOME_ROUTE
            } else {
                var startDestination = MainDestinations.LOGIN_ROUTE

            }


            EzCardTheme(darkTheme = isDarkTheme) {

                NavHost(
                    navController = ezCardNavController.navController,
                    startDestination = startDestination
                ) {
                    ezCardNavGraph(
                        upPress = { ezCardNavController.upPress() },
                        onNavigateToBottomBarRoute = { route ->
                            ezCardNavController.navigateToBottomBarRoute(
                                route
                            )
                        },
                        onNavigateToSubScreen = { route, navBackStackEntry ->
                            ezCardNavController.navigateToSubScreen(route, navBackStackEntry)
                        },
                        onNavigateAndPoppingBackStack = { route, navBackStackEntry ->
                            ezCardNavController.navigateAndPopAllBackStackEntries(
                                route,
                                navBackStackEntry
                            )
                        }
                    )
                }
            }
        }
    }
}


