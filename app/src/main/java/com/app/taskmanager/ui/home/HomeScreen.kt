package com.app.taskmanager.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.app.taskmanager.ui.theme.transparentGray

@Composable
fun HomeScreen(
    visualizer: HomeViewModel = viewModel(),
    modifier: Modifier = Modifier
) {

    val workspacesState by visualizer.workspacesState.collectAsState()

    LaunchedEffect(Unit) {
        visualizer.loadWorkspaces()
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        if (workspacesState.workspaces !== null) {
            item {
                Text(
                    text = "Workspaces",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleLarge
                )

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
        }
    }
}