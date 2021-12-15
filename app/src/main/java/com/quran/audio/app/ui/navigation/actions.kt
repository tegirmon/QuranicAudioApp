package com.quran.audio.app.ui.navigation

import androidx.navigation.NavHostController
import com.quran.audio.app.ui.navigation.Screen


class MainActions(navController: NavHostController) {
    val homeScreen: () -> Unit = {
        navController.navigate(Screen.Home.resourceId)
    }
    val suraViewScreen: () -> Unit = {
        navController.navigate(Screen.SuraList.resourceId)
    }
    val upPress: () -> Unit = {
        navController.navigateUp()
    }
}