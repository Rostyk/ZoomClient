package com.test.zoomclient.data.network.retrofit

import com.test.zoomclient.domain.model.Project
import kotlinx.serialization.Serializable
import retrofit2.http.GET


@Serializable
data class Template(var id: String, var name: String, var translation: String, var text: String,
    var textMultiLine: String, var radioButton: String)


data class ProjectInformation(var pdfType: String, var projectLeader: ProjectLeader)
data class ProjectLeader(var id: String)


interface ProjectAPI {
    @GET("api/v1/one/project")
    suspend fun projects(): ArrayList<Project>

}