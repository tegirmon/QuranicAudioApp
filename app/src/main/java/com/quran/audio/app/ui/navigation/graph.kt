package com.quran.audio.app.ui.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.android.exoplayer2.ExoPlayer
import com.quran.audio.app.R
import com.quran.audio.app.ui.media.MediaPlayer
import com.quran.audio.app.ui.media.MediaPlayerActions
import com.quran.audio.app.ui.data.MainViewModel
import com.quran.audio.app.ui.data.ReciterSelected
import com.quran.audio.app.ui.data.SuraSelected
import androidx.compose.runtime.livedata.observeAsState

@ExperimentalPagerApi
@ExperimentalMaterialApi
@Composable
fun MainNavGraph(
    navController: NavHostController,
    actions: MainActions,
    mainViewModel: MainViewModel,
    player: ExoPlayer
) {
    NavHost(navController, Screen.Home.route) {
        composable(Screen.Home.route) {
            Home(actions, mainViewModel)
        }
        composable(Screen.SuraList.route) {
            SuraView(actions, mainViewModel)
        }
        composable(Screen.Player.route) {
            val reciterSelected by mainViewModel.selectedReciter.observeAsState(ReciterSelected())
            val suraSelected by mainViewModel.selectedSura.observeAsState(SuraSelected())
            MediaPlayer(MediaPlayerActions(player), R.drawable.islamic_art, reciterSelected, suraSelected)
        }
    }
}