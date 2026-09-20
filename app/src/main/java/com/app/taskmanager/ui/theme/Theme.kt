package com.app.taskmanager.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.NavigationRailItemColors
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteColors
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteItemColors
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF5D60C1),
    onPrimary = Color.White,

    secondary = Color(0xFF7475D0),
    onSecondary = Color.White,

    tertiary = Color(0xFF8E8DDF),
    onTertiary = Color.White,

    background = Color(0xFFF9F7FC),
    onBackground = Color(0xFF1C1A20),

    surface = Color.White,
    onSurface = Color(0xFF1C1A20),

    error = Color(0xFFBA1A1A),
    onError = Color.White
)

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF8E8DDF),
    onPrimary = Color(0xFF282968),

    secondary = Color(0xFF7475D0),
    onSecondary = Color(0xFF282968),

    tertiary = Color(0xFFA9A9EE),
    onTertiary = Color(0xFF30305F),

    background = Color(0xFF121218),
    onBackground = Color(0xFFE6E1E9),

    surface = Color(0xFF1A191F),
    onSurface = Color(0xFFE6E1E9),

    error = Color(0xFFFFB4AB),
    onError = Color(0xFF690005)
)
@Composable
fun TaskManagerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

@Composable
fun taskManagerNavigationItemColors(): NavigationSuiteItemColors {
    return NavigationSuiteDefaults.itemColors(
        navigationBarItemColors = NavigationBarItemColors(
            selectedIconColor = Color.White,
            selectedTextColor = Purple5,
            selectedIndicatorColor = Purple2,
            unselectedIconColor = Purple4,
            unselectedTextColor = Purple4,
            disabledIconColor = Color.Gray,
            disabledTextColor = Color.Gray
        ),
        navigationRailItemColors = NavigationRailItemColors(
            selectedIconColor = Color.White,
            selectedTextColor = Purple5,
            selectedIndicatorColor = Purple2,
            unselectedIconColor = Purple4,
            unselectedTextColor = Purple4,
            disabledIconColor = Color.Gray,
            disabledTextColor = Color.Gray
        )
    )
}

@Composable
fun taskManagerNavigationSuiteColors(): NavigationSuiteColors {
    return NavigationSuiteDefaults.colors(
        shortNavigationBarContainerColor = Purple5,
        shortNavigationBarContentColor = Color.White,

        navigationBarContainerColor = Purple5,
        navigationBarContentColor = Color.White,

        navigationRailContainerColor = Purple5,
        navigationRailContentColor = Color.White,

        navigationDrawerContainerColor = Purple5,
        navigationDrawerContentColor = Color.White,
    )
}