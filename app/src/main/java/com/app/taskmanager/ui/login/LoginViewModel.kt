package com.app.taskmanager.ui.login

import androidx.compose.foundation.text.input.TextFieldState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.taskmanager.data.remote.ApiResult
import com.app.taskmanager.data.remote.UserService
import com.app.taskmanager.model.User
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {

    val userService = UserService()

    private val _events = MutableSharedFlow<UiEvent>()
    val events = _events.asSharedFlow()

    data class UiState (
        val isLoading: Boolean = false,
        val user: User? = null,
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

            if (!isLoginValid(email, password)){
                _uiState.update { it.copy(
                    isLoading = false,
                    isError = true
                ) }

                _events.emit(
                    UiEvent.ShowMessage("Email e senha devem estar preenchidos")
                )

                return@launch
            }

            when (
                val response = userService.login(
                email = email,
                password = password
                )
            ) {
                is ApiResult.Success -> {
                    _uiState.update { it.copy(
                        user = response.data.user,
                        isLoading = false
                    ) }

                    _events.emit(
                        UiEvent.ShowMessage("Login efetuado com sucesso!")
                    )
                }

                is ApiResult.Error -> {
                    _uiState.update { it.copy(
                        isLoading = false,
                        isError = true
                    ) }

                    _events.emit(
                        UiEvent.ShowMessage(
                            response.error?.message ?: "Error ao efetuar login"
                        )
                    )
                }
            }
        }
    }

    private fun isLoginValid (email: String, password: String): Boolean {
        return !(email.trim().isEmpty() || password.trim().isEmpty())
    }

    fun changeMode (mode: LoginMode) {
        this._uiState.update {
            it.copy(
                loginMode = mode,
                isError = false
            )
        }
    }
}