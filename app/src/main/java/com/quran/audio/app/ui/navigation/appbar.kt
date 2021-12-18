package com.quran.audio.app.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.quran.audio.app.R
import com.quran.audio.app.ui.data.ReciterSelected
import com.quran.audio.app.ui.data.SuraSelected
import com.quran.audio.app.ui.media.PlayerActions
import com.quran.audio.app.ui.media.PlayerControls
import com.quran.audio.app.ui.media.PlayerTitle

@Composable
fun TopBar() {
    TopAppBar(
        title = { Text(stringResource(R.string.app_name)) },
        backgroundColor = MaterialTheme.colors.primaryVariant,
    )
}

@Composable
fun BottomBar(navController: NavController, screens: List<Screen>) {
    BottomNavigation {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        screens.forEach { screen ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        screen.icon,
                        contentDescription = null
                    )
                },
                label = { Text(stringResource(screen.resourceId)) },
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Composable
fun PlayerBar(
    playerActions: PlayerActions,
    reciterSelected: ReciterSelected,
    suraSelected: SuraSelected
) {
    val relativePath = reciterSelected.reciter?.relativePath
    val suraId = suraSelected.sura?.id ?: 0
    val title = "${reciterSelected.reciter?.name} - ${suraSelected.sura?.name?.simple}"
    val borderColor = MaterialTheme.colors.primaryVariant
    val borderWidth = 4.dp
    Card(
        shape = RoundedCornerShape(0.dp),
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.background)
            .drawBehind {
                val strokeWidth = borderWidth.value * density
                drawLine(
                    borderColor,
                    Offset(0f, 0f),
                    Offset(size.width, 0f),
                    strokeWidth
                )
            }
    ) {
        Column(Modifier.padding(12.dp)) {
            PlayerTitle(title)
            Spacer(Modifier.height(16.dp))
            PlayerControls(playerActions, relativePath, suraId)
        }
    }
}