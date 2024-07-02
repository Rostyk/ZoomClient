package com.test.zoomclient.presentation.ui.screens.login

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.test.zoomclient.presentation.navigation.NavigationItem
import com.test.zoomclient.presentation.ui.screens.login.componenets.LoginForm


@Composable
fun LoginScreen(navController: NavController) {
    LoginForm { credentials ->
        if (credentials.login == "ross") {
            navController.navigate(NavigationItem.Home.route)
        }

    }
}