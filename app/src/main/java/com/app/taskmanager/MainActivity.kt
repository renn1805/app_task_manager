package com.app.taskmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.app.taskmanager.data.model.SessionManager
import com.app.taskmanager.ui.login.LoginScreen
import com.app.taskmanager.ui.main.MainApp
import com.app.taskmanager.ui.theme.TaskManagerTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SessionManager.initialize(this)

        enableEdgeToEdge()
        lifecycleScope.launch {
            SessionManager.restoreSession()
            setContent {
                TaskManagerTheme {
                    TaskManagerApp()
                }
            }
        }
    }
}

@PreviewScreenSizes
@Composable
fun TaskManagerApp() {
    val user by SessionManager.user.collectAsState()

    if (user == null) {
        LoginScreen()
    } else {
        MainApp()
    }
}