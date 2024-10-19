package com.test.zoomclient.data.network.retrofit

import com.test.zoomclient.domain.model.Project
import retrofit2.http.GET


data class milestone(var id: String, var number: String, var leader: String, var description: String,
    var name: String, var translatedPdf: Boolean, var pdf: String, var hash: String,
    var isActiveUser: Boolean, var status: String, var isOnSiteSupportOnly: Boolean,
    var email: String, var emailCC: String, var startDate: String, var endDate: String,
    var createdAt: String, var updatedAt: String,
    var template: Template)

data class Template(var id: String, var name: String, var translation: String, var text: String,
    var textMultiLine: String, var radioButton: String)


data class ProjectInformation(var pdfType: String, var projectLeader: ProjectLeader)
data class ProjectLeader(var id: String)


interface ProjectAPI {
    @GET("api/v1/one/project")
    suspend fun projects(): ArrayList<Project>

}