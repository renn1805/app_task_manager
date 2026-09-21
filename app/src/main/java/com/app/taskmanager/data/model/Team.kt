package com.app.taskmanager.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Team(
    val id: String,
    val name: String,
    val description: String,
    val workspaceId: String,
    val managerId: String
)