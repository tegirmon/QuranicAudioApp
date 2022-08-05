package com.quran.audio.app.ui.appbar

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FeaturedPlayList
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.quran.audio.app.R
import com.quran.audio.app.ui.navigation.NavActions
import com.quran.audio.app.ui.navigation.Screen

@Composable
fun TopBar(actions: NavActions, navController: NavHostController) {
    TopAppBar(
        title = { Text(stringResource(R.string.app_name)) },
        backgroundColor = MaterialTheme.colors.primaryVariant,
        navigationIcon = {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            if (currentDestination?.route != Screen.Home.route) {
                IconButton(onClick = { actions.upPress() }) {
                    Icon(Icons.Filled.ArrowBack, "")
                }
            }
        },
        actions = {
            IconButton(onClick = { actions.playListScreen() }) {
                Icon(Icons.Filled.FeaturedPlayList, "")
            }
        }

    )
}
