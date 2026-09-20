package com.app.taskmanager.ui.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.app.taskmanager.R
import com.app.taskmanager.ui.login.LoginScreen
import com.app.taskmanager.ui.theme.TaskManagerTheme
import com.app.taskmanager.ui.theme.taskManagerNavigationItemColors
import com.app.taskmanager.ui.theme.taskManagerNavigationSuiteColors

@PreviewScreenSizes
@Composable
fun MainApp() {

    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val navigationItemColors = taskManagerNavigationItemColors()
    val navigationSuiteColors = taskManagerNavigationSuiteColors()

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
                    onClick = { navController.navigate(destination.route) },
                    colors = navigationItemColors,
                )
            }
        },
        navigationSuiteColors =  navigationSuiteColors
    ) {
        NavHost(
            modifier = Modifier.padding(20.dp),
            navController = navController,
            startDestination = AppDestinations.HOME.route,
        ){
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