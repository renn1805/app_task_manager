package com.app.taskmanager.data.remote


import android.util.Log
import com.app.taskmanager.model.User
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.contentType
import io.ktor.http.ContentType
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
    suspend fun login(email: String, password: String): LoginResponse? {
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

            return response.body<LoginResponse>()

        } catch (e: Exception) {
            Log.e("ApiService", "Erro na requisição: ${e.message}", e)
            return  null
        }
    }

}