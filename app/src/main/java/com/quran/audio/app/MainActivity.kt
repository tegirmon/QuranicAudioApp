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
import com.quran.audio.app.ui.navigation.*
import com.quran.audio.app.ui.data.MainViewModel
import com.quran.audio.app.ui.data.ReciterSelected
import com.quran.audio.app.ui.data.SuraSelected
import com.quran.audio.app.ui.media.MediaPlayerActions
import com.quran.audio.app.ui.theme.QuranicAudioAppTheme


@ExperimentalMaterialApi
@ExperimentalPagerApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mainViewModel =
            MainViewModel(DataSource(getString(R.string.quranicaudio_api_url)))
        setContent {
            QuranicAudioAppTheme {
                val navController = rememberNavController()
                val actions = remember(navController) { MainActions(navController) }
                val player = ExoPlayer.Builder(applicationContext).build()
                val mediaPlayerActions = MediaPlayerActions(player)
                val reciterSelected by mainViewModel.selectedReciter.observeAsState(ReciterSelected())
                val suraSelected by mainViewModel.selectedSura.observeAsState(SuraSelected())
                Scaffold(
                    topBar = { TopBar() },
                    bottomBar = { PlayerBar(mediaPlayerActions, reciterSelected, suraSelected) }
                ) {
                    MainNavGraph(navController, actions, mainViewModel, mediaPlayerActions)
                }
            }
        }
    }
}
