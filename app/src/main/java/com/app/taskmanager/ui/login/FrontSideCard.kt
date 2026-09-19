package com.app.taskmanager.ui.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.app.taskmanager.ui.theme.Typography
import com.app.taskmanager.ui.theme.frontSideCardColor
import com.app.taskmanager.ui.theme.primaryLightPurple
import com.app.taskmanager.ui.theme.primaryPurple

@Composable
fun FrontSideCard(
    onClickSubscribe: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            space = 15.dp,
            alignment = Alignment.CenterVertically
        )
    ) {

        Text(
            text = "Bem-vindo!",
            style = Typography.titleLarge.copy(
                color = Color.White
            )
        )

        Text(
            text = "Entre na nossa plataforma e descubra uma nova experiência.",
            style = Typography.bodyLarge.copy(
                color = Color.White,
                textAlign = TextAlign.Center
            )
        )

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 50.dp),
            onClick = onClickSubscribe,
            shape = shapes.medium,
            colors = ButtonColors(
                containerColor = Color.White,
                contentColor = primaryPurple,
                disabledContainerColor = Color.Transparent,
                disabledContentColor = primaryLightPurple
            )
        ) {
            Text(
                text = "Inscrever-se",
                style = Typography.titleLarge.copy(
                    color = frontSideCardColor,
                )
            )
        }
    }
}