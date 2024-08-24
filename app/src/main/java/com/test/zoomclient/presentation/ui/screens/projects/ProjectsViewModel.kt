package com.test.zoomclient.presentation.ui.screens.projects

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.test.zoomclient.domain.model.Project
import com.test.zoomclient.domain.model.repository.ProjectRepository
import kotlinx.coroutines.launch
import java.io.IOException
import java.lang.Exception

sealed interface ProjectUiState {
    data class Loaded(val projects: ArrayList<Project>) : ProjectUiState
    object Error : ProjectUiState
    object Loading : ProjectUiState
    object Initial : ProjectUiState
}

class ProjectsViewModel(private val projectRepository: ProjectRepository) : ViewModel() {
    var projectUiState: ProjectUiState by mutableStateOf(ProjectUiState.Initial)
    fun loadProjects() {
        viewModelScope.launch {
            projectUiState = ProjectUiState.Loading

            try {
                val projects = projectRepository.loadProjects()
                projectUiState = ProjectUiState.Loaded(projects)

            } catch (e: IOException) {
                projectUiState = ProjectUiState.Error
            } catch (e: Exception) {
                projectUiState = ProjectUiState.Error
            }
        }
    }
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                ProjectsViewModel(projectRepository = ProjectRepository())
            }
        }
    }
}