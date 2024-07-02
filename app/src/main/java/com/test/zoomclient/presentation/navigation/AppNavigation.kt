package com.test.zoomclient.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BusinessCenter
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.test.zoomclient.presentation.ui.screens.drawer.DrawerScreen
import com.test.zoomclient.presentation.ui.screens.drawer.componenents.DrawerMenuItem
import com.test.zoomclient.presentation.ui.screens.login.LoginScreen
import com.test.zoomclient.presentation.ui.screens.projects.ProjectsScreen
import com.test.zoomclient.presentation.ui.screens.settings.SettingsScreen
import kotlinx.coroutines.CoroutineScope

enum class Screen {
    LOGIN,
    HOME,
}
public sealed class NavigationItem(val route: String) {
    object Home : NavigationItem(Screen.HOME.name)
    object Login : NavigationItem(Screen.LOGIN.name)
}

enum class Drawer {
    PROJECTS,
    SETTINGS,
    LOGOUT
}

object DrawerNavigation {
    object Projects : NavigationItem(Drawer.PROJECTS.name)
    object Settings : NavigationItem(Drawer.SETTINGS.name)
    object Logout : NavigationItem(Drawer.LOGOUT.name)
}


@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationItem.Login.route,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavigationItem.Login.route) {
            LoginScreen(navController)
        }

        composable(NavigationItem.Home.route) {
            val projectsItem = DrawerMenuItem(Icons.Filled.BusinessCenter, "Projects", DrawerNavigation.Projects.route)
            DrawerScreen(currentScreen = remember { mutableStateOf(projectsItem) })
        }
    }
}