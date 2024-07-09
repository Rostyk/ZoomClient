package com.test.zoomclient.presentation.ui.screens.login

import LoginViewModel
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.test.zoomclient.presentation.navigation.NavigationItem
import com.test.zoomclient.presentation.ui.screens.login.componenets.LoginForm


@Composable
fun LoginScreen(navController: NavController) {
    val loginViewModel: LoginViewModel = viewModel(factory = LoginViewModel.Factory)

    LoginForm(loginViewModel) { credentials ->
        if (credentials.login == "ross") {
            navController.navigate(NavigationItem.Home.route)
        }

    }
}