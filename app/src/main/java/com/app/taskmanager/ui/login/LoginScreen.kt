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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    visualizer: LoginViewModel = viewModel(),
) {

    val globalState by visualizer.uiState.collectAsState()

    val context = LocalContext.current

    LaunchedEffect(globalState.message) {
        globalState.message?.let { message ->
            Toast.makeText(
                context,
                message,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    LaunchedEffect(globalState.user) {
        globalState.user?.let {
            onLoginSuccess()
        }
    }

    val emailFieldState = TextFieldState()
    val passwordFieldState = TextFieldState()
    val nameFieldState = TextFieldState()
    val confirmPasswordFieldState = TextFieldState()


    LoginLayout(
        mode = globalState.loginMode,
        loginContent = {
            AnimatedVisibility(globalState.loginMode == LoginMode.LOGIN){
                LoginLayerContent(
                    onLogin = {
                        visualizer.login(
                            email = emailFieldState.text.toString(),
                            password = passwordFieldState.text.toString()
                        )
                    },
                    emailState = emailFieldState,
                    passwordState = passwordFieldState,
                    onChangeToRegistration = {visualizer.changeMode(LoginMode.REGISTRATION)}
                )
            }
        },
        registerContent = {
            AnimatedVisibility(globalState.loginMode == LoginMode.REGISTRATION) {
                RegistrationLayerContent(
                    onRegister = {},
                    onChangeToLogin = { visualizer.changeMode(LoginMode.LOGIN) },
                    nameState = nameFieldState,
                    emailState = emailFieldState,
                    passwordState = passwordFieldState,
                    confirmPasswordState = confirmPasswordFieldState
                )
            }
        }
    )
}