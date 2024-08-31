package com.test.zoomclient.presentation.ui.screens.drawer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.BusinessCenter
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.test.zoomclient.presentation.navigation.DrawerNavigation
import com.test.zoomclient.presentation.theme.ZoomClientTheme
import com.test.zoomclient.presentation.ui.screens.drawer.componenents.DrawerMenuItem
import com.test.zoomclient.presentation.ui.screens.projects.ProjectsScreen
import com.test.zoomclient.presentation.ui.screens.settings.SettingsScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

val menus = arrayOf(
    DrawerMenuItem(Icons.Filled.BusinessCenter, "Projects", DrawerNavigation.Projects.route),
    DrawerMenuItem(Icons.Filled.Settings, "Settings", DrawerNavigation.Settings.route),
    DrawerMenuItem(Icons.Filled.Logout, "Log out", DrawerNavigation.Logout.route)
)

@Composable
fun DrawerScreen(
    currentScreen: MutableState<DrawerMenuItem>,
    navController: NavHostController,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    drawerState: DrawerState = androidx.compose.material3.rememberDrawerState(initialValue = DrawerValue.Open)
) {
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(drawerContainerColor=Color.Gray) {

                DrawerContent(menus) { route ->
                    coroutineScope.launch {
                        drawerState.close()
                    }

                    val item = menus.find { i -> i.route == route }
                    currentScreen.value = item!!
                }
            }
        }
    ) {

        when (currentScreen.value.route) {
            DrawerNavigation.Projects.route -> ProjectsScreen(drawerState = drawerState, navController)
            DrawerNavigation.Settings.route -> SettingsScreen(drawerState = drawerState)
        }

    }
}

@Composable
private fun DrawerContent(
    menus: Array<DrawerMenuItem>,
    onMenuClick: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()//.background(Color.Blue),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier.size(150.dp),
                imageVector = Icons.Filled.AccountCircle,
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        menus.forEach { item ->
            NavigationDrawerItem(
                label = { Text(text = item.title, color = Color.White) },
                icon = { Icon(imageVector = item.icon, contentDescription = null) },
                selected = false,
                onClick = {
                    onMenuClick(item.route)
                }
            )
        }
    }
}

@Preview(showBackground = true, device = "id:Nexus One", showSystemUi = true)
@Composable
fun DrawerScreenPreviewDark() {
    ZoomClientTheme(darkTheme = true) {
        val projectsItem = DrawerMenuItem(Icons.Filled.BusinessCenter, "Projects", DrawerNavigation.Projects.route)
        DrawerScreen(currentScreen = remember { mutableStateOf(projectsItem) }, navController = rememberNavController())
    }
}