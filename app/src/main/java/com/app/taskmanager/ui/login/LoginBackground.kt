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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.dp
import com.app.taskmanager.ui.theme.Purple40
import com.app.taskmanager.ui.theme.Purple80
import com.app.taskmanager.ui.theme.frontSideCardColor
import com.app.taskmanager.ui.theme.primaryPurple
import kotlin.io.path.Path

@Composable
fun LoginBackground(
    content: @Composable () -> Unit
) {

    var startAnimation by remember { mutableStateOf(false) }

    val firstHeight by animateFloatAsState(
        targetValue = if (startAnimation) 0.8f else 0f,
        animationSpec = tween(durationMillis = 1200, easing = FastOutSlowInEasing),
        label = "HeightAnimation"
    )

    val secondHeight by animateFloatAsState(
        targetValue = if (startAnimation) 0.75f else 0f,
        animationSpec = tween(durationMillis = 1700, easing = FastOutSlowInEasing),
        label = "HeightAnimation"
    )

    val thirdHeight by animateFloatAsState(
        targetValue = if (startAnimation) 0.7f else 0f,
        animationSpec = tween(durationMillis = 2200, easing = FastOutSlowInEasing),
        label = "HeightAnimation"
    )

    LaunchedEffect(Unit) {
        startAnimation = true
    }

    Box(
        Modifier
            .fillMaxSize()
            .background(color = primaryPurple),
        ) {
        Box(
            Modifier
                .fillMaxHeight(firstHeight)
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
                        color = Purple80
                    )
                }
                .align(Alignment.BottomCenter),
        )

        Box(
            Modifier
                .fillMaxHeight(secondHeight)
                .fillMaxWidth()
                .drawBehind {
                    drawPath(
                        path = Path().apply {
                            addRoundRect(
                                RoundRect(
                                    rect = Rect(0f, 0f, size.width, size.height),
                                    // Definimos raios específicos para cada quina
                                    topLeft = CornerRadius(150.dp.toPx(), 150.dp.toPx()),
                                    topRight = CornerRadius(150.dp.toPx(), 150.dp.toPx()),
                                    bottomRight = CornerRadius.Zero, // Reto embaixo
                                    bottomLeft = CornerRadius.Zero   // Reto embaixo
                                )
                            )
                        },
                        color = frontSideCardColor
                    )
                }
                .align(Alignment.BottomCenter),
        )

        Box(
            Modifier
                .fillMaxHeight(thirdHeight)
                .fillMaxWidth()
                .drawBehind {
                    drawPath(
                        path = Path().apply {
                            addRoundRect(
                                RoundRect(
                                    rect = Rect(0f, 0f, size.width, size.height),
                                    // Definimos raios específicos para cada quina
                                    topLeft = CornerRadius(150.dp.toPx(), 150.dp.toPx()),
                                    topRight = CornerRadius(150.dp.toPx(), 150.dp.toPx()),
                                    bottomRight = CornerRadius.Zero, // Reto embaixo
                                    bottomLeft = CornerRadius.Zero   // Reto embaixo
                                )
                            )
                        },
                        color = Purple40
                    )
                }
                .align(Alignment.BottomCenter),

            ){
            content()
        }
    }
}