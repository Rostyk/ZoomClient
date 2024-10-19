package com.test.zoomclient.presentation.ui.screens.projects

import android.util.Log
import android.widget.TextView
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BusinessCenter
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.gson.GsonBuilder
import com.test.zoomclient.R
import com.test.zoomclient.domain.model.Project
import com.test.zoomclient.presentation.navigation.DrawerNavigation
import com.test.zoomclient.presentation.navigation.NavigationItem
import com.test.zoomclient.presentation.theme.ZoomClientTheme
import com.test.zoomclient.presentation.ui.screens.Greeting
import com.test.zoomclient.presentation.ui.screens.drawer.DrawerScreen
import com.test.zoomclient.presentation.ui.screens.drawer.componenents.CustomAppBar
import com.test.zoomclient.presentation.ui.screens.drawer.componenents.DrawerMenuItem
import kotlinx.coroutines.CoroutineScope

@Composable
fun ProjectsScreen( drawerState: DrawerState,
                    navController: NavHostController
) {

    val viewModel: ProjectsViewModel = viewModel(factory = ProjectsViewModel.Factory)


    viewModel.loadProjects()

    Scaffold(modifier = Modifier
        .fillMaxSize(),
        topBar = {
            CustomAppBar(
                drawerState = drawerState,
                title = "Projects"
            )
        }
    )
    { paddingValues  ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
        ) {

            val projectState = viewModel.projectUiState
            when (projectState) {
                is ProjectUiState.Error -> Text(text = "Error")
                is ProjectUiState.Loaded -> {
                    val projects = projectState.projects

                    val gson = GsonBuilder().create()
                    projects.forEach { projects ->
                        ProjectItem(projects){ project ->
                            val milestoneJson = gson.toJson(project.milestone)
                            navController.navigate(NavigationItem.Milestones.route + "/$milestoneJson")

                        }
                        Spacer(modifier = Modifier.size(5.dp))
                    }
                }

                is ProjectUiState.Loading -> {
                    CircularProgressIndicator(
                        color = colorResource(id = R.color.teal_700),
                        strokeWidth = 5.dp

                    ) }

                else -> {
                }
            }

        }
    }
}


@Composable
fun ProjectItem(projectt: Project, callback: (project: Project) -> Unit){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        onClick = {
            callback(projectt)
        }
    ) {
        Column {
            Text(text ="name: ${projectt.name}")
            Spacer(modifier = Modifier.width(8.dp))
            Text(text ="Id: ${projectt.id}")
        }
    }
}


/*
@Preview(showBackground = true, device = "id:Nexus One", showSystemUi = true)
@Composable
fun ProjectScreenPreviewDark() {
    ZoomClientTheme(darkTheme = true) {
        val projectssItem = DrawerMenuItem(Icons.Filled.BusinessCenter, "Projects", DrawerNavigation.Projects.route)
        ProjectsScreen(currentScreen = remember { mutableStateOf(projectssItem) })
    }
}
8
 */