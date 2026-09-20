package com.app.taskmanager.data.remote

import io.ktor.http.HttpStatusCode

sealed interface ApiResult <out T> {
    data class Success<T> (
        val data: T,
        val status: HttpStatusCode
    ) : ApiResult<T>

    data class Error(
        val status: HttpStatusCode?,
        val error: ApiError?
    ) : ApiResult<Nothing>
}