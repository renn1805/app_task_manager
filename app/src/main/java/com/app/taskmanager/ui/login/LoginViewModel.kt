package com.app.taskmanager.ui.login

import androidx.compose.foundation.text.input.TextFieldState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.taskmanager.data.remote.UserService
import com.app.taskmanager.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {

    val userService = UserService()

    data class UiState (
        val isLoading: Boolean = false,
        val user: User? = null,
        val message: String? = null,
        val isError: Boolean = false,
        val loginMode: LoginMode = LoginMode.LOGIN
    )

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState


    fun login (
        email: String,
        password: String
    ) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            val response = userService.login(
                email = email,
                password =  password
            )

            if (response?.user != null) {
                _uiState.update { it.copy(
                    isLoading = false,
                    message = "Login feito com sucesso",
                    user = response.user
                ) }
            } else {
                _uiState.update { it.copy(
                    isLoading = false,
                    message = "Erro ao efetuar o Login",
                    isError = true
                ) }
            }

        }
    }

    fun changeMode (mode: LoginMode) {
        this._uiState.update { it.copy(loginMode = mode) }
    }

}