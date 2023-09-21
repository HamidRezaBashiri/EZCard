package com.hamidrezabashiri.ezcard.ui.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.core.os.LocaleListCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import com.hamidrezabashiri.ezcard.ui.navigation.MainDestinations
import com.hamidrezabashiri.ezcard.ui.navigation.ezCardNavGraph
import com.hamidrezabashiri.ezcard.ui.navigation.rememberEzCardNavController
import com.hamidrezabashiri.ezcard.ui.theme.EzCardTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.takeWhile
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags("fa"))

            val ezCardNavController = rememberEzCardNavController()

            val mainViewModel: MainViewModel = hiltViewModel()

            val isSystemInDarkTheme = isSystemInDarkTheme()
            var isDarkTheme by remember {
                mutableStateOf(isSystemInDarkTheme)
            }


            var startDestination by remember { mutableStateOf<String?>(null) }

            var shouldCancelCollection by remember { mutableStateOf(false) }

            DisposableEffect(shouldCancelCollection) {
                val job = Job()
                val scope = CoroutineScope(Dispatchers.Main + job)

                // Launch a coroutine to collect the Flow with takeWhile
                scope.launch {
                    mainViewModel.isFirstLogin
                        .takeWhile { !shouldCancelCollection }
                        .collect { it ->
                            startDestination = if (it) {
                                MainDestinations.WELCOME_ROUTE
                            } else {
                                MainDestinations.LOGIN_ROUTE
                            }
                            shouldCancelCollection = true
                        }
                }

                onDispose {
                    // Set shouldCancelCollection to true to stop the collection
                    shouldCancelCollection = true
                    // Don't forget to cancel the job and scope when the Composable is disposed
                    job.cancel()
                }
            }


            if (startDestination != null) {
                EzCardTheme(darkTheme = isDarkTheme) {
                    NavHost(
                        navController = ezCardNavController.navController,
                        startDestination = startDestination!!
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

}


