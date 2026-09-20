package com.app.taskmanager.ui.login

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.app.taskmanager.ui.theme.Typography
import com.app.taskmanager.R
import com.app.taskmanager.ui.theme.primaryLightPurple
import com.app.taskmanager.ui.theme.primaryPurple
@Composable
fun RegistrationLayerContent(
    onRegister: () -> Unit,
    onChangeToLogin: () -> Unit,
    nameState: TextFieldState,
    emailState: TextFieldState,
    passwordState: TextFieldState,
    confirmPasswordState: TextFieldState,
) {
    var showPassword by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        Text(
            text = "Criar sua conta",
            style = Typography.titleLarge.copy(color = Color.White)
        )

        LoginTextField(
            textFieldState = nameState,
            placeholder = "Nome"
        )

        LoginTextField(
            textFieldState = emailState,
            placeholder = "E-mail"
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            LoginTextField(
                textFieldState = passwordState,
                placeholder = "Senha",
                outputTransformation = {
                    if (!showPassword) {
                        replace(0, length, "* ".repeat(length))
                    }
                },
                widthFraction = 0.8f
            )

            IconButton(
                onClick = { showPassword = !showPassword },
                modifier = Modifier.size(50.dp)
            ) {
                Icon(
                    painter = painterResource(
                        if (showPassword)
                            R.drawable.view_password
                        else
                            R.drawable.hidden_password
                    ),
                    contentDescription = if (showPassword)
                        "Ocultar senha"
                    else
                        "Mostrar senha",
                    tint = Color.White
                )
            }
        }
        LoginTextField(
            textFieldState = confirmPasswordState,
            placeholder = "Confirmar senha",
            outputTransformation = {
                if (!showPassword) {
                    replace(0, length, "* ".repeat(length))
                }
            }
        )

        Spacer(Modifier.height(10.dp))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            onClick = onRegister,
            shape = shapes.medium,
            colors = ButtonColors(
                containerColor = Color.White,
                contentColor = primaryPurple,
                disabledContainerColor = Color.Transparent,
                disabledContentColor = primaryLightPurple
            )
        ) {
            Text("CADASTRAR")
        }

        OutlinedButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            onClick = onChangeToLogin,
            shape = shapes.medium,
            border = BorderStroke(1.dp, Color.White)
        ) {
            Text("JÁ TENHO CONTA")
        }
    }
}