package com.raj.instaclone.screens.holder

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.raj.instaclone.components.AppBottomNav
import com.raj.instaclone.providers.LocalNavHost
import com.raj.instaclone.screens.chats.ChatsScreen
import com.raj.instaclone.screens.home.HomeScreen
import com.raj.instaclone.screens.login.LoginScreen
import com.raj.instaclone.screens.notifications.NotificationScreen
import com.raj.instaclone.screens.profile.ProfileScreen
import com.raj.instaclone.screens.search.SearchScreen
import com.raj.instaclone.screens.splash.SplashScreen
import com.raj.instaclone.sealed.Screen

@Composable
fun HolderScreen(
    onStatusBarColorChange: (color: Color) -> Unit,
) {
    val destinations = remember {
        listOf(Screen.Home, Screen.Search, Screen.AddPost, Screen.Notifications, Screen.Profile)
    }
    val controller = LocalNavHost.current
    val currentDestinationAsState = getActiveRoute(navController = controller)

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            NavHost(
                modifier = Modifier.weight(1f),
                navController = controller,
                startDestination = Screen.Splash.route
            ) {
                composable(Screen.Splash.route) {
                    onStatusBarColorChange(MaterialTheme.colors.background)
                    SplashScreen()
                }
                composable(Screen.Login.route) {
                    onStatusBarColorChange(MaterialTheme.colors.background)
                    LoginScreen()
                }
                composable(Screen.Home.route) {
                    onStatusBarColorChange(MaterialTheme.colors.background)
                    HomeScreen()
                }
                composable(Screen.Search.route) {
                    onStatusBarColorChange(MaterialTheme.colors.background)
                    SearchScreen()
                }
                composable(Screen.AddPost.route) {
                    onStatusBarColorChange(MaterialTheme.colors.background)
                }
                composable(Screen.Notifications.route) {
                    onStatusBarColorChange(MaterialTheme.colors.background)
                    NotificationScreen()
                }
                composable(Screen.Profile.route) {
                    onStatusBarColorChange(MaterialTheme.colors.background)
                    ProfileScreen()
                }
                composable(Screen.Chats.route) {
                    onStatusBarColorChange(MaterialTheme.colors.background)
                    ChatsScreen()
                }
            }
            Divider()
            if (currentDestinationAsState in destinations.map { it.route }) {
                AppBottomNav(
                    activeRoute = currentDestinationAsState,
                    backgroundColor = MaterialTheme.colors.background,
                    bottomNavDestinations = destinations,
                    onActiveRouteChange = {
                        if (it != currentDestinationAsState) {
                            /** We should navigate to that new route */
                            controller.navigate(it) {
                                popUpTo(Screen.Home.route) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    }
                )
            }
        }
    }
}

/**
 * A function that is used to get the active route in our Navigation Graph , should return the splash route if it's null
 */
@Composable
fun getActiveRoute(navController: NavHostController): String {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route ?: "splash"
}
