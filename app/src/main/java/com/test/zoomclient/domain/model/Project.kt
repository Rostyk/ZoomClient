package com.test.zoomclient.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Project (var id: String, var name: String, var milestones: ArrayList<Milestone>)