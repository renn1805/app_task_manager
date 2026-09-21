package com.app.taskmanager.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.app.taskmanager.ui.theme.Purple3
import com.app.taskmanager.ui.theme.transparentGray
import com.app.taskmanager.R
import com.app.taskmanager.ui.home.workspace.CreateWorkspaceDialog
import com.app.taskmanager.ui.theme.Purple4
import com.app.taskmanager.ui.theme.Purple5
import com.app.taskmanager.ui.theme.backgroundCard

@Composable
fun HomeScreen(
    visualizer: HomeViewModel = viewModel(),
    modifier: Modifier = Modifier
) {

    val workspacesState by visualizer.workspacesState.collectAsState()

    LaunchedEffect(Unit) {
        visualizer.loadWorkspaces()
    }

    var showCreateDialog by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        if (workspacesState.workspaces !== null) {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "Workspaces",
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleLarge
                    )

                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(backgroundCard)
                            .clickable {
                                showCreateDialog = true
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(R.drawable.baseline_add_24),
                            contentDescription = null,
                            modifier = Modifier.size(30.dp),
                            colorFilter = ColorFilter.tint(Purple4)
                        )
                    }
                }

                Spacer(Modifier.height(10.dp))

                BoxWithConstraints(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    LazyRow(
                        modifier = Modifier.fillMaxHeight(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        items(items = workspacesState.workspaces!!.workspaces) { workspace ->
                            WorkspaceCard(
                                workspace = workspace,
                                width = maxWidth * 0.85f
                            )
                        }
                    }
                }
            }

            item {
                HorizontalDivider(
                    modifier = Modifier.fillMaxWidth(),
                    color = Purple5
                )
            }

        }
    }

    if (showCreateDialog) {
        CreateWorkspaceDialog(
            onDismiss = {
                showCreateDialog = false
            },
            onCreate = { name, description ->
                // chamar ViewModel depois
                showCreateDialog = false
            }
        )
    }
}