package com.app.taskmanager.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.app.taskmanager.R
import com.app.taskmanager.data.model.CondensedWorkspace
import com.app.taskmanager.ui.theme.Purple2
import com.app.taskmanager.ui.theme.Purple3
import com.app.taskmanager.ui.theme.backgroundCard
import com.app.taskmanager.ui.theme.transparentGray

@Composable
fun WorkspaceCard(
    workspace: CondensedWorkspace,
    width: Dp
) {
    Box(
        modifier = Modifier
            .width(width)
            .height(170.dp)
            .background(
                color = backgroundCard,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Ícone + status
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Image(
                    painter = painterResource(R.drawable.baseline_folder_24),
                    contentDescription = null,
                    modifier = Modifier.size(40.dp),
                    colorFilter = ColorFilter.tint(Purple3)
                )

                Box(
                    modifier = Modifier
                        .background(
                            color = Purple3,
                            shape = RoundedCornerShape(50)
                        )
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Text(
                        text = if (workspace.completedAt == null) "ATIVO" else "CONCLUÍDO",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Nome
            Text(
                text = workspace.projectName.uppercase(),
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black,
                fontWeight = FontWeight.ExtraBold
            )

            // Descrição
            Text(
                text = workspace.description.uppercase(),
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Bold,
                color = Color.Gray,
                maxLines = 2
            )

            Spacer(modifier = Modifier.weight(1f))

            // Membros + tarefas
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row {
                    workspace.members
                        .take(3)
                        .forEachIndexed { index, member ->
                            Box(
                                modifier = Modifier
                                    .size(24.dp)
                                    .offset(x = (-index * 6).dp)
                                    .background(
                                        color = MaterialTheme.colorScheme.secondaryContainer,
                                        shape = CircleShape
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = member.nameMember
                                        .firstOrNull()
                                        ?.uppercase()
                                        ?: "?",
                                    style = MaterialTheme.typography.labelSmall
                                )
                            }
                        }
                }

                Text(
                    text = "Tasks",
                    color = Color.Black,
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }
    }
}