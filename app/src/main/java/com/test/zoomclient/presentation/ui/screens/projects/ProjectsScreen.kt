package com.test.zoomclient.presentation.ui.screens.projects

import android.util.Log
import android.widget.TextView
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.test.zoomclient.R
import com.test.zoomclient.domain.model.Project
import com.test.zoomclient.presentation.ui.screens.drawer.componenents.CustomAppBar
import kotlinx.coroutines.CoroutineScope

@Composable
fun ProjectsScreen( drawerState: DrawerState) {

    val viewModel: ProjectsViewModel = viewModel(factory = ProjectsViewModel.Factory)


    val myProjects = ArrayList<Project>()
    val project1 = Project(id = "2214", name = "syyyyf")
    val project2 = Project(id = "14", name = "Name")
    val project3 = Project(id = "2212144", name = "fggnf")
    val project4 = Project(id = "Vkd", name = "ssffsf")
    myProjects.add(project1)
    myProjects.add(project2)
    myProjects.add(project3)
    myProjects.add(project4)


    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Scaffold(
            topBar = {
                CustomAppBar(
                    drawerState = drawerState,
                    title = "Projects"
                )
            }
        )
        { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                val projectState = viewModel.projectUiState
                when (projectState) {
                    is ProjectUiState.Error -> Text(text = "Error")
                    is ProjectUiState.Loaded -> {
                        val projects = projectState.projects
                        myProjects.forEach { project ->
                            ProjectItem(project)
                        }
                    }

                    is ProjectUiState.Loading -> {
                        CircularProgressIndicator(
                            color = colorResource(id = R.color.teal_700),
                            strokeWidth = 5.dp
                        )
                    }

                    else -> {

                        }
                    }
                }
            }
        }
    }

@Composable
fun ProjectItem(projectt: Project){
    Card(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column {
            Text(text ="name: ${projectt.name}")
            Text(text ="Id: ${projectt.id}")
        }
    }
}