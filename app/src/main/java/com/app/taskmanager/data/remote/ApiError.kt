package com.app.taskmanager.data.remote

import kotlinx.serialization.Serializable

@Serializable
data class ApiError (
    val code: String,
    val message: String,
    val details: String? = null
) {
}