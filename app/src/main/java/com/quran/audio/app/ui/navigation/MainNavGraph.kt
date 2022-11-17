package com.quran.audio.app.ui.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.accompanist.pager.ExperimentalPagerApi
import com.quran.audio.app.data.viewmodel.PlayListViewModel
import com.quran.audio.app.data.viewmodel.ReciterViewModel
import com.quran.audio.app.data.viewmodel.SuraViewModel
import com.quran.audio.app.ui.media.MediaPlayerActions

@ExperimentalPagerApi
@ExperimentalMaterialApi
@Composable
fun MainNavGraph(
    navController: NavHostController,
    mediaPlayerActions: MediaPlayerActions,
    playListViewModel: PlayListViewModel,
    reciterViewModel: ReciterViewModel,
    suraViewModel: SuraViewModel
) {
    LaunchedEffect(Unit, block = {
        reciterViewModel.getReciterList()
        suraViewModel.getSuraList()
    })
    NavHost(navController, Screen.Home.route) {
        composable(Screen.Home.route) {
            Home(reciterViewModel, suraViewModel, playListViewModel, mediaPlayerActions)
        }
        composable(Screen.PlayList.route) {
            PlayListView(playListViewModel, reciterViewModel, suraViewModel)
        }
    }
}