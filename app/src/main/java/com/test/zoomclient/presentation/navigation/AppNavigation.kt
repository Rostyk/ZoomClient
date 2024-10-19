package com.test.zoomclient.presentation.navigation

import android.util.Log
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.test.zoomclient.data.network.retrofit.milestone
import com.test.zoomclient.domain.model.Project
import com.test.zoomclient.presentation.ui.screens.drawer.DrawerScreen
import com.test.zoomclient.presentation.ui.screens.drawer.componenents.DrawerMenuItem
import com.test.zoomclient.presentation.ui.screens.login.LoginScreen
import com.test.zoomclient.presentation.ui.screens.milestones.MilestonesScreen
import kotlinx.coroutines.CoroutineScope

enum class Screen {
    LOGIN,
    HOME,
    MILESTONES,
}

sealed class NavigationItem(val route: String) {
    object Login : NavigationItem(Screen.LOGIN.name)
    object DRAWER : NavigationItem(Screen.HOME.name)
    object Milestones : NavigationItem(Screen.MILESTONES.name)
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

        composable(NavigationItem.DRAWER.route) {
            val projectsItem = DrawerMenuItem(Icons.Filled.BusinessCenter, "Projects", DrawerNavigation.Projects.route)
            DrawerScreen(currentScreen = remember { mutableStateOf(projectsItem) }, navController)
        }
        composable("${NavigationItem.Milestones.route}/{projectMilestone}") { navBackStackEntry ->
            val gson: Gson = GsonBuilder().create()
            val projectMilestoneJson = navBackStackEntry.arguments?.getString("projectMilestone")

            if (projectMilestoneJson != null) {
                val projectMilestone = gson.fromJson(projectMilestoneJson, milestone::class.java)
                MilestonesScreen(navController, projectMilestone)
            } else {
                Log.v("Error", "Route found null")
            }
        }
        }
    }