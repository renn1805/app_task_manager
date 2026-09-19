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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.app.taskmanager.ui.login.LoginScreen
import com.app.taskmanager.ui.theme.TaskManagerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TaskManagerTheme {
                TaskManagerApp()
            }
        }
    }
}

@PreviewScreenSizes
@Composable
fun TaskManagerApp() {

    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationSuiteScaffold(
        navigationSuiteItems = {
            AppDestinations.entries.forEach { destination ->
                item(
                    icon = {
                        Icon(
                            ImageVector.vectorResource(destination.icon),
                            contentDescription = destination.label
                        )
                    },
                    label = { Text(destination.label) },
                    selected = currentRoute == destination.route,
                    onClick = { navController.navigate(destination.route) }
                )
            }
        }
    ) {
        NavHost(
            modifier = Modifier.padding(20.dp),
            navController = navController,
            startDestination = "login",
        ){
            composable (route = "login") {
                LoginScreen()
            }
            composable (route = AppDestinations.HOME.route){
                Text(text = "home")
            }
            composable (route = AppDestinations.WORKSPACES.route){
                Text(text = "workspaces")
            }
            composable (route = AppDestinations.PROFILE.route){
                Text(text = "profile")
            }
        }
    }
}

enum class AppDestinations(
    val route: String,
    val label: String,
    val icon: Int,
) {
    HOME("home","Home", R.drawable.outline_home_24),
    WORKSPACES("workspaces","Workspaces", R.drawable.outline_workspaces_24),
    PROFILE("profile", "Profile", R.drawable.outline_account_circle_24),
}
