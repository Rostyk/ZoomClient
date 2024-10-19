package com.test.zoomclient.domain.model

import com.test.zoomclient.data.network.retrofit.Template
import kotlinx.serialization.Serializable

@Serializable
data class Milestone(var id: String, var number: String, var leader: String, var description: String,
                     var name: String, var translatedPdf: Boolean, var pdf: String, var hash: String,
                     var isActiveUser: Boolean, var status: String, var isOnSiteSupportOnly: Boolean,
                     var email: String, var emailCC: String, var startDate: String, var endDate: String,
                     var createdAt: String, var updatedAt: String,
                     var template: Template
)