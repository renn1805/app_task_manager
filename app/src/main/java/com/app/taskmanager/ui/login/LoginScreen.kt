package com.app.taskmanager.ui.login

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.app.taskmanager.data.model.SessionManager

@Composable
fun LoginScreen(
    visualizer: LoginViewModel = viewModel(),
) {

    val globalState by visualizer.uiState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        visualizer.events.collect { event ->
            when (event) {
                is UiEvent.ShowMessage -> {
                    Toast.makeText(
                        context,
                        event.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    val emailFieldState = TextFieldState()
    val passwordFieldState = TextFieldState()
    val nameFieldState = TextFieldState()
    val confirmPasswordFieldState = TextFieldState()

    LoginLayout(
        mode = globalState.loginMode,
        loginContent = {
            if (!globalState.isLoading) {
                AnimatedVisibility(globalState.loginMode == LoginMode.LOGIN) {
                    LoginLayerContent(
                        onLogin = {
                            visualizer.login(
                                email = emailFieldState.text.toString(),
                                password = passwordFieldState.text.toString()
                            )
                        },
                        emailState = emailFieldState,
                        passwordState = passwordFieldState,
                        onChangeToRegistration = { visualizer.changeMode(LoginMode.REGISTRATION) },
                        isError = globalState.isError
                    )
                }
            } else {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        color = Color.White
                    )
                }
            }
        },
        registerContent = {
            if (!globalState.isLoading) {
                AnimatedVisibility(globalState.loginMode == LoginMode.REGISTRATION) {
                    RegistrationLayerContent(
                        onRegister = { visualizer.register(
                            name = nameFieldState.text.toString(),
                            email = emailFieldState.text.toString(),
                            password = passwordFieldState.text.toString(),
                            confirmPassword = confirmPasswordFieldState.text.toString()
                        ) },
                        onChangeToLogin = { visualizer.changeMode(LoginMode.LOGIN) },
                        nameState = nameFieldState,
                        emailState = emailFieldState,
                        passwordState = passwordFieldState,
                        confirmPasswordState = confirmPasswordFieldState,
                        isError = globalState.isError
                    )
                }
            } else {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        color = Color.White
                    )
                }
            }
        }
    )
}