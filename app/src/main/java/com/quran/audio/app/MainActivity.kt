package com.quran.audio.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.android.exoplayer2.ExoPlayer
import com.quran.audio.app.data.DataSource
import com.quran.audio.app.data.model.PlayListItemModel
import com.quran.audio.app.data.viewmodel.PlayListViewModel
import com.quran.audio.app.data.viewmodel.ReciterViewModel
import com.quran.audio.app.data.viewmodel.SuraViewModel
import com.quran.audio.app.ui.appbar.PlayerBottomBar
import com.quran.audio.app.ui.appbar.TopBar
import com.quran.audio.app.ui.media.MediaPlayerActions
import com.quran.audio.app.ui.navigation.MainNavGraph
import com.quran.audio.app.ui.navigation.NavActions
import com.quran.audio.app.ui.theme.QuranicAudioAppTheme


@ExperimentalMaterialApi
@ExperimentalPagerApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val player = ExoPlayer.Builder(applicationContext).build()
        val mediaPlayerActions = MediaPlayerActions(player)
        val reciterViewModel = ReciterViewModel(DataSource(getString(R.string.quranicaudio_api_url)))
        val suraViewModel = SuraViewModel(DataSource(getString(R.string.quranicaudio_api_url)))
        val playListViewModel = PlayListViewModel()
        playListViewModel.createPlayList("test")
        playListViewModel.addToPlayList(1, 6, 55)
        playListViewModel.addToPlayList(1, 1, 55)
        playListViewModel.addToPlayList(1, 3, 55)
        setContent {
            QuranicAudioAppTheme {
                val navController = rememberNavController()
                val actions = remember(navController) { NavActions(navController) }
                Scaffold(
                    topBar = { TopBar(actions, navController) },
                    bottomBar = { PlayerBottomBar(mediaPlayerActions, playListViewModel) }
                ) {
                    it.calculateBottomPadding()
                    MainNavGraph(
                        navController,
                        mediaPlayerActions,
                        playListViewModel,
                        reciterViewModel,
                        suraViewModel
                    )
                }
            }
        }
    }
}
