package com.app.taskmanager.ui.login

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.app.taskmanager.ui.theme.Typography

@Composable
fun LoginScreen(visualizer: LoginViewModel = viewModel()) {

    val globalState by visualizer.uiState.collectAsState()

    val rotation by animateFloatAsState(
        targetValue = if (globalState.cardRotated) 180f else 0f,
        animationSpec = tween(durationMillis = 2000)
    )


    LoginBackground(
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(all = 20.dp)
            ) {
                if (globalState.user == null){
                    LoginCard(
                        modifier = Modifier
                            .graphicsLayer {
                                rotationY = rotation
                                cameraDistance = 12f * density
                            }
                            .align(Alignment.TopCenter)
                            .heightIn(min = 300.dp),
                        content = @Composable {
                            if (rotation <= 90f)
                                FrontSideCard(
                                    onClickSubscribe = { visualizer.rotate() }
                                );
                            else
                                BackSideCard(
                                    onClickSubmit = { visualizer.login() },
                                    userNameState = globalState.emailFieldState,
                                    passwordState = globalState.passwordFieldState,
                                    modifier = Modifier
                                        .graphicsLayer {
                                            rotationY = if (rotation > 90f) 180f else 0f
                                        },
                                );
                        }
                    )

                } else {
                    Column (
                        verticalArrangement = Arrangement.spacedBy(15.dp, Alignment.CenterVertically),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.align(Alignment.Center)
                    ){
                        Text(
                            text = "${globalState.user!!.name}",
                            style = Typography.titleLarge.copy(
                                color = Color.White
                            )
                        )
                        Text(
                            text = "id: ${globalState.user!!.id}",
                            style = Typography.titleMedium.copy(
                                color = Color.White
                            )
                        )
                        Text(
                            text = "email: ${globalState.user!!.email}",
                            style = Typography.titleMedium.copy(
                                color = Color.White
                            )
                        )
                        Text(
                            text = "position: ${globalState.user!!.position}",
                            style = Typography.titleLarge.copy(
                                color = Color.White
                            )
                        )
                    }
                }

            }
        }
    )
}