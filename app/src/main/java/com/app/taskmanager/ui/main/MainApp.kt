package com.app.taskmanager.ui.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.app.taskmanager.R
import com.app.taskmanager.data.model.SessionManager
import com.app.taskmanager.ui.home.HomeScreen
import com.app.taskmanager.ui.login.LoginScreen
import com.app.taskmanager.ui.theme.Purple3
import com.app.taskmanager.ui.theme.TaskManagerTheme
import com.app.taskmanager.ui.theme.Typography
import com.app.taskmanager.ui.theme.taskManagerNavigationItemColors
import com.app.taskmanager.ui.theme.taskManagerNavigationSuiteColors
import kotlinx.coroutines.launch

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
        val scope = rememberCoroutineScope()

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onBackground
        ){
            NavHost(
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 40.dp),
                navController = navController,
                startDestination = AppDestinations.HOME.route,
            ){
                composable (route = AppDestinations.HOME.route){
                    HomeScreen()
                }
                composable (route = AppDestinations.WORKSPACES.route){}
                composable(route = AppDestinations.PROFILE.route) {
                    Column (
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        Spacer(Modifier.weight(1f))
                        Button(
                            modifier = Modifier
                                .height(50.dp)
                                .fillMaxWidth(),
                            onClick = {
                                scope.launch {
                                    SessionManager.logout()
                                }
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.error
                            ),
                            shape = RoundedCornerShape(50)
                        ) {
                            Text(
                                text = "Sair",
                                style = MaterialTheme.typography.labelLarge,
                                color = MaterialTheme.colorScheme.onError,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }            }
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