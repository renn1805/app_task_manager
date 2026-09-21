package com.app.taskmanager.data.remote


import android.util.Log
import com.app.taskmanager.data.model.User
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.contentType
import io.ktor.http.ContentType
import io.ktor.http.isSuccess
import kotlinx.serialization.Serializable

class UserService {
    @Serializable
    data class LoginRequest (
        val email: String,
        val password: String
    )
    @Serializable
    data class LoginResponse (
        val user: User
    )

    suspend fun login(email: String, password: String): ApiResult<LoginResponse> {
        try {

            val response = ApiClient.client.post("${ApiClient.BASE_URL}/users/login") {
                contentType(ContentType.Application.Json)
                setBody(
                    LoginRequest(
                        email,
                        password
                    )
                )
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
                    data = response.body<LoginResponse>()
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
                    message = e.message ?: "Erro desconhecido ao realizar a requisição"
                )
            )
        }
    }

    @Serializable
    data class RegistrationRequest(
        val email: String,
        val name: String,
        val password: String
    )
    typealias RegistrationResponse = LoginResponse
    suspend fun register(
        email: String,
        name: String,
        password: String
    ): ApiResult<RegistrationResponse> {
        try {

            val response = ApiClient.client.post("${ApiClient.BASE_URL}/users") {
                contentType(ContentType.Application.Json)
                setBody(
                    RegistrationRequest(
                        email = email,
                        name = name,
                        password = password
                    )
                )
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
                    data = response.body<RegistrationResponse>()
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
                        ?: "Erro desconhecido ao realizar a requisição"
                )
            )
        }
    }

}