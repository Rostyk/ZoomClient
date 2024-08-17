package com.test.zoomclient.domain.model.repository

import com.test.zoomclient.data.network.retrofit.ProjectAPI
import com.test.zoomclient.data.network.retrofit.RetrofitClient
import com.test.zoomclient.domain.model.Project
import com.test.zoomclient.domain.model.User
import retrofit2.create

class ProjectRepository{
    private val client = RetrofitClient.getInstance()

    suspend fun loadProjects(): ArrayList<Project> {

        val projectAPI = client.create(ProjectAPI::class.java)
        val result = projectAPI.projects()
        return result

    }

}