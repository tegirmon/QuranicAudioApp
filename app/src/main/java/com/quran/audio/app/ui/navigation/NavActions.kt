package com.quran.audio.app.ui.navigation

import androidx.navigation.NavHostController


class NavActions(navController: NavHostController) {
    val playListScreen: () -> Unit = {
        navController.navigate(Screen.PlayList.route)
    }
    val suraViewScreen: () -> Unit = {
        navController.navigate(Screen.SuraList.route)
    }
    val upPress: () -> Unit = {
        navController.navigateUp()
    }
}