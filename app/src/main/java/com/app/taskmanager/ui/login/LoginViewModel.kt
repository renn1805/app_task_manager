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
        val emailFieldState: TextFieldState = TextFieldState(),
        val passwordFieldState: TextFieldState = TextFieldState(),
        val cardRotated: Boolean = false,
        val user: User? = null
    )

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState

    fun rotate () {
        _uiState.update { it.copy( cardRotated = !it.cardRotated) }
    }

    fun login () {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            val response = userService.login(
                email = _uiState.value.emailFieldState.text.toString(),
                password =  _uiState.value.passwordFieldState.text.toString()
            )

            _uiState.update { it.copy(
                isLoading = false,
                user = response?.user
            ) }
        }
    }

}