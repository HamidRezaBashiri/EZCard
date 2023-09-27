package com.hamidrezabashiri.ezcard.ui.screens

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.os.LocaleListCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import com.hamidrezabashiri.ezcard.ui.common.BottomNavBar
import com.hamidrezabashiri.ezcard.ui.navigation.MainDestinations
import com.hamidrezabashiri.ezcard.ui.navigation.ezCardNavGraph
import com.hamidrezabashiri.ezcard.ui.navigation.rememberEzCardNavController
import com.hamidrezabashiri.ezcard.ui.theme.EzCardTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()

        super.onCreate(savedInstanceState)
        setContent {

            AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags("fa"))

            val ezCardNavController = rememberEzCardNavController()

            val mainViewModel: MainViewModel = hiltViewModel()

            val isSystemInDarkTheme = isSystemInDarkTheme()
            var isDarkTheme by remember {
                mutableStateOf(isSystemInDarkTheme)
            }


            val currentRouteFlow by ezCardNavController.currentRouteFlow.collectAsState(initial = null)
//            var shouldCancelCollection by remember { mutableStateOf(false) }


//            DisposableEffect(shouldCancelCollection) {
//                val job = Job()
//                val scope = CoroutineScope(Dispatchers.Main + job)
//
//                // Launch a coroutine to collect the Flow with takeWhile
//                scope.launch {
//                    mainViewModel.isFirstLogin
//                        .takeWhile { !shouldCancelCollection }
//                        .collect { it ->
//                            startDestination = if (it) {
//                                MainDestinations.WELCOME_ROUTE
//                            } else {
//                                MainDestinations.LOGIN_ROUTE
//                            }
//                            shouldCancelCollection = true
//                        }
//                }
//
//                onDispose {
//                    // Set shouldCancelCollection to true to stop the collection
//                    shouldCancelCollection = true
//                    // Don't forget to cancel the job and scope when the Composable is disposed
//                    job.cancel()
//                }
//            }

            EzCardTheme(darkTheme = isDarkTheme) {

                Scaffold(
                    bottomBar = {
                        if (currentRouteFlow == MainDestinations.HOME_ROUTE ||
                            currentRouteFlow == MainDestinations.WALLET_ROUTE
                            || currentRouteFlow == MainDestinations.SETTINGS_ROUTE
                        ) {
                            BottomNavBar(
                                currentRoute = ezCardNavController.currentRoute,
                                onNavItemClicked = { route ->
                                    ezCardNavController.navigateToBottomBarRoute(
                                        route
                                    )
                                })
                        }
                    }) {

                    NavHost(
                        modifier = Modifier.padding(it),
                        navController = ezCardNavController.navController,
                        startDestination = MainDestinations.LOGIN_ROUTE
                    ) {
                        ezCardNavGraph(
                            isDarkTheme,
                            upPress = { ezCardNavController.upPress() },
                            onNavigateWithParams = { param, navBackStack, route ->
                                ezCardNavController.navigateWithParam(
                                    param = param,
                                    from = navBackStack,
                                    route = route
                                )
                            },
                            onNavigateToSubScreen = { route, navBackStackEntry ->
                                ezCardNavController.navigateToSubScreen(
                                    route,
                                    navBackStackEntry
                                )
                            }
                        ) { route, navBackStackEntry ->
                            ezCardNavController.navigateAndPopAllBackStackEntries(
                                route,
                                navBackStackEntry
                            )
                        }
                    }
                }
            }

        }
    }

}


