package com.quran.audio.app.ui.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.android.exoplayer2.ExoPlayer
import com.quran.audio.app.R
import com.quran.audio.app.ui.media.MediaPlayer
import com.quran.audio.app.ui.media.MediaPlayerActions
import com.quran.audio.app.ui.data.MainViewModel

@ExperimentalPagerApi
@ExperimentalMaterialApi
@Composable
fun MainNavGraph(
    navController: NavHostController,
    actions: MainActions,
    mainViewModel: MainViewModel,
    player: ExoPlayer
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            Home(
                actions,
                mainViewModel
            )
        }
        composable(
            Screen.SuraList.route
        ) { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            SuraView(
                actions,
                mainViewModel,
                arguments.getString(NavArgs.RECITER_RELATIVE_PATH)
            )
        }
        composable(Screen.Player.route) { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            val reciterRelativePath =
                arguments.getString(NavArgs.RECITER_RELATIVE_PATH)
            val suraId = arguments.getInt(NavArgs.SURA_ID)
            MediaPlayer(
                "$reciterRelativePath - $suraId",
                MediaPlayerActions(player),
                R.drawable.islamic_art,
                reciterRelativePath,
                suraId
            )
        }
    }
}