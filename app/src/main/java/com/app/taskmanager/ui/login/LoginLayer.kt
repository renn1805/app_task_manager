package com.app.taskmanager.ui.login

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.dp
import com.app.taskmanager.ui.theme.Purple80
import com.app.taskmanager.ui.theme.primaryPurple

@Composable
fun LoginLayer(
    modifier: Modifier = Modifier,
    duration: Int = 1200,
    targetValue: Float,
    backgroundColor: Color,
    content: @Composable () -> Unit = {}
) {

    val height by animateFloatAsState(
        targetValue = targetValue,
        animationSpec = tween(durationMillis = duration, easing = FastOutSlowInEasing),
        label = "HeightAnimation"
    )


    Box(
        modifier
            .fillMaxHeight(height)
            .fillMaxWidth()
            .drawBehind {
                drawPath(
                    path = Path().apply {
                        addRoundRect(
                            RoundRect(
                                rect = Rect(0f, 0f, size.width, size.height),
                                topLeft = CornerRadius(150.dp.toPx(), 150.dp.toPx()),
                                topRight = CornerRadius(150.dp.toPx(), 150.dp.toPx()),
                                bottomRight = CornerRadius.Zero,
                                bottomLeft = CornerRadius.Zero
                            )
                        )
                    },
                    color = backgroundColor
                )
            }
            .padding(horizontal = 20.dp, vertical = 60.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        content()
    }
}