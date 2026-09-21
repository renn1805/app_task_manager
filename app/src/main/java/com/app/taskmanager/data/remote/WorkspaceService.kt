package com.app.taskmanager.data.remote


import android.util.Log
import com.app.taskmanager.data.model.CondensedWorkspaceResponse
import com.app.taskmanager.data.model.User
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.contentType
import io.ktor.http.ContentType
import io.ktor.http.isSuccess
import kotlinx.serialization.Serializable

class WorkspaceService {



        suspend fun getWorkspaces(userId: String): ApiResult<CondensedWorkspaceResponse> {
            try {

                val response = ApiClient.client.get("${ApiClient.BASE_URL}/workspaces") {
                    parameter("user", userId)
                }

                Log.d(
                    "ApiService",
                    """
                API RESPONSE
                ├── Status: ${response.status}
                ├── Headers: ${response.headers}
                └── Body:
                ${response.bodyAsText()}
                """.trimIndent()
                )

                return if (response.status.isSuccess()) {
                    ApiResult.Success(
                        status = response.status,
                        data = response.body<CondensedWorkspaceResponse>()
                    )
                } else {
                    ApiResult.Error(
                        status = response.status,
                        error = response.body<ApiError>()
                    )
                }

            } catch (e: Exception) {
                Log.e("ApiService", "Erro na requisição: ${e.message}", e)

                return ApiResult.Error(
                    status = null,
                    error = ApiError(
                        code = "REQUEST_ERROR",
                        message = e.message
                            ?: "Erro desconhecido ao buscar workspaces"
                    )
                )
            }
        }
    }
