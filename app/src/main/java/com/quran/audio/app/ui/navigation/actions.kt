package com.quran.audio.app.ui.navigation

import androidx.core.os.bundleOf
import androidx.navigation.NavHostController


class MainActions(navController: NavHostController) {
    val homeScreen: () -> Unit = {
        navController.navigate(Screen.Home.resourceId)
    }
    val suraViewScreen: (reciterRelativePath: String) -> Unit = { reciterRelativePath ->
        navController.navigate(
            Screen.SuraList.route, bundleOf(
                NavArgs.RECITER_RELATIVE_PATH to reciterRelativePath
            )
        )
    }
    val mediaPlayerView: (reciterRelativePath: String, suraId: Int) -> Unit =
        { reciterRelativePath, suraId ->
            navController.navigate(
                Screen.Player.route, bundleOf(
                    NavArgs.RECITER_RELATIVE_PATH to reciterRelativePath,
                    NavArgs.SURA_ID to suraId
                )
            )
        }
    val upPress: () -> Unit = {
        navController.navigateUp()
    }
}