package com.quran.audio.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.android.exoplayer2.ExoPlayer
import com.quran.audio.app.data.DataSource
import com.quran.audio.app.ui.media.MediaPlayer
import com.quran.audio.app.ui.media.MediaPlayerActions
import com.quran.audio.app.ui.navigation.*
import com.quran.audio.app.ui.reciter.ReciterViewModel
import com.quran.audio.app.ui.section.SectionViewModel
import com.quran.audio.app.ui.sura.SuraViewModel
import com.quran.audio.app.ui.theme.QuranicAudioAppTheme


@ExperimentalMaterialApi
@ExperimentalPagerApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sectionViewModel = SectionViewModel(DataSource(getString(R.string.quranicaudio_api_url)))
        val reciterViewModel = ReciterViewModel(DataSource(getString(R.string.quranicaudio_api_url)))
        val suraViewModel = SuraViewModel(DataSource(getString(R.string.quranicaudio_api_url)))
        setContent {
            QuranicAudioAppTheme {
                val items = listOf(
                    Screen.Home,
                    Screen.SuraList,
                    Screen.Player,
                )
                val navController = rememberNavController()
                val actions = remember(navController) { MainActions(navController) }
                val player = ExoPlayer.Builder(applicationContext).build()
                Scaffold(
                    topBar = { TopBar() },
                    bottomBar = { BottomBar(navController, items) }
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = Screen.Home.route
                    ) {
                        composable(Screen.Home.route) {
                            Home(
                                actions,
                                sectionViewModel,
                                reciterViewModel
                            )
                        }
                        composable(
                            Screen.SuraList.route
                        ) { backStackEntry ->
                            val arguments = requireNotNull(backStackEntry.arguments)
                            SuraView(
                                actions,
                                suraViewModel,
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
            }
        }
    }
}
