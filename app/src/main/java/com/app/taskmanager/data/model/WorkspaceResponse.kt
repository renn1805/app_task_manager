package com.app.taskmanager.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class WorkspaceResponse(
    val workspace: Workspace
)

@Serializable
data class Workspace(
    val id: String,
    @SerialName("project_name")
    val projectName: String,
    val description: String,
    val createdAt: String,
    val completedAt: String?,
    val managerId: String,
    val members: List<WorkspaceMember>,
    val teams: List<Team>,
//    val tasks: List<Task>,
//    val objectives: List<Objective>
)

@Serializable
data class WorkspaceMember(
    val id: String,
    val member: User
)