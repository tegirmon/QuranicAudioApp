package com.quran.audio.app.ui.navigation

import androidx.navigation.NavHostController


class MainActions(navController: NavHostController) {
    val homeScreen: () -> Unit = {
        navController.navigate(Screen.Home.resourceId)
    }
    val suraViewScreen: () -> Unit = {
        navController.navigate(Screen.SuraList.route)
    }
    val mediaPlayerView: () -> Unit = {
        navController.navigate(Screen.Player.route)
    }
    val upPress: () -> Unit = {
        navController.navigateUp()
    }
}