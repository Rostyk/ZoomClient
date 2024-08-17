package com.test.zoomclient.data.network.retrofit

import com.test.zoomclient.domain.model.Project
import retrofit2.http.GET


data class milestone(var id: String)

data class Template(var id: String)


data class ProjectInformation(var pdfType: String)
data class ProjectLeader(var id: String)


interface ProjectAPI {
    @GET("api/v1/one/project")
    suspend fun projects(): ArrayList<Project>

}