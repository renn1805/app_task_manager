package com.app.taskmanager.ui.login

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.OutputTransformation
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp
import com.app.taskmanager.ui.theme.Typography

@Composable
fun LoginTextField (
    textFieldState: TextFieldState,
    placeholder: String,
    outputTransformation: OutputTransformation? = null,
    widthFraction: Float = 1f
) {
    BasicTextField(
        state = textFieldState,
        modifier = Modifier
            .fillMaxWidth(widthFraction)
            .height(height = 50.dp),
        enabled = true,
        readOnly = false,
        inputTransformation = null,
        textStyle = Typography.bodyLarge.copy(
            color = Color.White
        ),
        keyboardOptions = KeyboardOptions(),
        onKeyboardAction = null,
        lineLimits = TextFieldLineLimits.SingleLine,
        onTextLayout = null,
        interactionSource = null,
        cursorBrush = SolidColor(value = Color.White),
        outputTransformation = outputTransformation,
        decorator = { input ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.Transparent, shape = shapes.medium)
                    .border(width = 1.dp, color = Color.White, shape = shapes.medium)
                    .padding(horizontal = 20.dp, vertical = 10.dp),
                contentAlignment = Alignment.CenterStart,

                ) {
                if (textFieldState.text.isEmpty()) Text(
                    text = placeholder,
                    style = Typography.bodyLarge.copy(
                        color = Color.LightGray
                    )
                )
                input()
            }
        },
        scrollState = rememberScrollState(),
    )

}