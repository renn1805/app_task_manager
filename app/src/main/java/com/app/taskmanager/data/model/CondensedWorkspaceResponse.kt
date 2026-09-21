package com.app.taskmanager.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CondensedWorkspaceResponse(
    val workspaces: List<CondensedWorkspace>
)

@Serializable
data class CondensedWorkspace(
    val id: String,
    @SerialName("project_name")
    val projectName: String,
    val description: String,
    val createdAt: String,
    val completedAt: String?,
    val managerId: String,
    val members: List<CondensedWorkspaceMember>,
    val teams: List<Team>
)

@Serializable
data class CondensedWorkspaceMember(
    val id: String,
    val memberId: String,
    val nameMember: String
)