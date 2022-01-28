package com.quran.audio.app.ui.navigation

import androidx.navigation.NavHostController


class MainActions(navController: NavHostController) {
    val suraViewScreen: () -> Unit = {
        navController.navigate(Screen.SuraList.route)
    }
    val upPress: () -> Unit = {
        navController.navigateUp()
    }
}