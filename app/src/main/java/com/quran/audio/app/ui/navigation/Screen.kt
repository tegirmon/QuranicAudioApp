package com.quran.audio.app.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.quran.audio.app.R
import com.quran.audio.app.data.model.CurrentReciter
import com.quran.audio.app.data.model.PlayListItemEnriched
import com.quran.audio.app.data.viewmodel.PlayListViewModel
import com.quran.audio.app.data.viewmodel.ReciterViewModel
import com.quran.audio.app.data.viewmodel.SuraViewModel
import com.quran.audio.app.ui.component.playlist.PlayList
import com.quran.audio.app.ui.component.suralist.ReciterSelector
import com.quran.audio.app.ui.component.suralist.SuraList
import com.quran.audio.app.ui.media.AudioUriParser
import com.quran.audio.app.ui.media.MediaPlayerActions

@Composable
fun Home(
    reciterViewModel: ReciterViewModel,
    suraViewModel: SuraViewModel,
    playListViewModel: PlayListViewModel,
    mediaPlayerActions: MediaPlayerActions
) {
    Column {
        val currentReciter: CurrentReciter by reciterViewModel.currentReciter
        ReciterSelector(
            reciterViewModel.reciterList,
            currentReciter
        ) { reciter ->
            reciterViewModel.selectReciter(reciter)
        }
        Spacer(modifier = Modifier.requiredHeight(1.dp))
        SuraList(
            suraViewModel.suraList,
            { sura ->
                currentReciter.reciter?.let { reciter ->
                    playListViewModel.playNow(PlayListItemEnriched(0, sura, reciter, 0))
                    mediaPlayerActions.addToPlaylist(listOf(AudioUriParser.parse(reciter.relativePath, sura.id)))
                    mediaPlayerActions.play()
                }
            },
            { playList, sura ->
                currentReciter.reciter?.let { reciter ->
                    playListViewModel.addToPlayList(playList.id, sura, reciter)
                }
            },
            playListViewModel.playLists.values.toList()
        )
        Spacer(modifier = Modifier.requiredHeight(8.dp))
    }
}

@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun PlayListView(
    viewModel: PlayListViewModel,
    mediaPlayerActions: MediaPlayerActions
) {
    Column {
        PlayList(viewModel, mediaPlayerActions)
        Spacer(modifier = Modifier.requiredHeight(8.dp))
    }
}

sealed class Screen(val route: String, @StringRes val resourceId: Int, val icon: ImageVector) {
    object Home : Screen("home", R.string.home, Icons.Filled.Home)
    object PlayList : Screen("playList", R.string.play_list, Icons.Filled.List)
}
