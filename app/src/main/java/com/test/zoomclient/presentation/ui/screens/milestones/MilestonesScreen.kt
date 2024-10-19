package com.test.zoomclient.presentation.ui.screens.milestones

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.test.zoomclient.domain.model.Milestone
import com.test.zoomclient.domain.model.Project
import com.test.zoomclient.presentation.navigation.NavigationItem

@Composable
fun MilestonesScreen(navController: NavHostController, projectMilestone: ArrayList<Milestone>)
{
    Column {
            Text(text ="id: ${projectMilestone.first().name}")
        }
    }