package com.app.taskmanager.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.taskmanager.data.model.SessionManager
import com.app.taskmanager.data.remote.ApiResult
import com.app.taskmanager.data.remote.UserService
import com.app.taskmanager.data.model.User
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
                    SessionManager.login(response.data.user)
                    _uiState.update { it.copy(
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

    fun register(
        name: String,
        email: String,
        password: String,
        confirmPassword: String
    ) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            val validationError = validateRegistration(
                name = name,
                email = email,
                password = password,
                confirmPassword = confirmPassword
            )

            if (validationError != null) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        isError = true
                    )
                }

                _events.emit(
                    UiEvent.ShowMessage(validationError)
                )

                return@launch
            }

            when (
                val response = userService.register(
                    email = email,
                    name = name,
                    password = password
                )
            ) {
                is ApiResult.Success -> {
                    SessionManager.login(response.data.user)
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            isError = false
                        )
                    }

                    _events.emit(
                        UiEvent.ShowMessage("Cadastro efetuado com sucesso!")
                    )
                }

                is ApiResult.Error -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            isError = true
                        )
                    }

                    _events.emit(
                        UiEvent.ShowMessage(
                            response.error?.message
                                ?: "Erro ao efetuar cadastro"
                        )
                    )
                }
            }
        }
    }

    private fun validateRegistration(
        name: String,
        email: String,
        password: String,
        confirmPassword: String
    ): String? {

        if (name.trim().isEmpty())
            return "O nome deve ser preenchido"

        if (email.trim().isEmpty())
            return "O email deve ser preenchido"

        if (password.isEmpty())
            return "A senha deve ser preenchida"

        if (confirmPassword.isEmpty())
            return "A confirmação de senha deve ser preenchida"

        if (password.length < 8)
            return "A senha deve ter pelo menos 8 caracteres"

        if (password != confirmPassword)
            return "As senhas não coincidem"

        return null
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