package com.app.taskmanager.ui.login

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.dp
import com.app.taskmanager.ui.theme.Purple1
import com.app.taskmanager.ui.theme.Purple2
import com.app.taskmanager.ui.theme.Purple3
import com.app.taskmanager.ui.theme.Purple4
import com.app.taskmanager.ui.theme.Purple5

@Composable
fun LoginLayout(
    loginContent: @Composable () -> Unit = {},
    registerContent: @Composable () -> Unit = {},
    mode: LoginMode
) {

    Box(
        Modifier
            .fillMaxSize()
            .background(color = Purple1),
        contentAlignment = Alignment.BottomCenter
    ) {

        var startAnimation by remember { mutableStateOf(false) }

        LaunchedEffect(Unit) {
            startAnimation = true
        }

        LoginLayer(
            duration = 1200,
            targetValue = if (startAnimation) 0.9f else 0f,
            backgroundColor = Purple2
        )
        LoginLayer(
            duration = 1200,
            targetValue = if (startAnimation) 0.86f else 0f,
            backgroundColor = Purple3
        )
        LoginLayer(
            duration = 1500,
            targetValue = if (startAnimation) 0.82f else 0f,
            backgroundColor = Purple4,
            content = loginContent
        )
        LoginLayer(
            targetValue = if (mode == LoginMode.REGISTRATION) 0.82f else 0f,
            backgroundColor = Purple5,
            content = registerContent
        )
    }

}