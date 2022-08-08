package com.quran.audio.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.android.exoplayer2.ExoPlayer
import com.quran.audio.app.data.DataSource
import com.quran.audio.app.ui.appbar.PlayerBottomBar
import com.quran.audio.app.ui.appbar.TopBar
import com.quran.audio.app.ui.data.MainViewModel
import com.quran.audio.app.ui.data.ReciterSelected
import com.quran.audio.app.ui.data.SuraSelected
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
        val mainViewModel =
            MainViewModel(DataSource(getString(R.string.quranicaudio_api_url)), mediaPlayerActions)
        setContent {
            QuranicAudioAppTheme {
                val navController = rememberNavController()
                val actions = remember(navController) { NavActions(navController) }
                val reciterSelected by mainViewModel.selectedReciter.observeAsState(ReciterSelected())
                val suraSelected by mainViewModel.selectedSura.observeAsState(SuraSelected())
                Scaffold(
                    topBar = { TopBar(actions, navController) },
                    bottomBar = { PlayerBottomBar(mediaPlayerActions, reciterSelected, suraSelected) }
                ) {
                    it.calculateBottomPadding()
                    MainNavGraph(navController, mainViewModel, mediaPlayerActions)
                }
            }
        }
    }
}
