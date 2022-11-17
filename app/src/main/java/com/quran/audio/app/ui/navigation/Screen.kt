package com.quran.audio.app.ui.navigation

import android.util.Log
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
import com.quran.audio.app.data.viewmodel.PlayListViewModel
import com.quran.audio.app.data.viewmodel.ReciterViewModel
import com.quran.audio.app.data.viewmodel.SuraViewModel
import com.quran.audio.app.ui.component.playlist.PlayList
import com.quran.audio.app.ui.component.suralist.ReciterSelector
import com.quran.audio.app.ui.component.suralist.SuraList
import com.quran.audio.app.ui.data.MainViewModel
import com.quran.audio.app.ui.data.ReciterSelected
import com.quran.audio.app.ui.media.AudioUriParser
import com.quran.audio.app.ui.media.MediaPlayerActions

@Composable
fun Home(viewModel: MainViewModel, mediaPlayerActions: MediaPlayerActions) {
    Column {
        val selectedReciter: ReciterSelected by viewModel.selectedReciter
        Log.d("Home", "Home: $selectedReciter")
        ReciterSelector(
            viewModel.reciterList,
            selectedReciter
        ) { reciter ->
            viewModel.selectReciter(reciter)
        }
        Spacer(modifier = Modifier.requiredHeight(1.dp))
        SuraList(
            viewModel.suraList,
            {
                viewModel.selectSura(it)
                val relativePath = selectedReciter.reciter?.relativePath
                mediaPlayerActions.playPause(AudioUriParser.parse(relativePath, it.id))
            },
            { viewModel.addToPlaylist(it, 0) }
        )
        Spacer(modifier = Modifier.requiredHeight(8.dp))
    }
}

@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun PlayListView(
    viewModel: PlayListViewModel,
    reciterViewModel: ReciterViewModel,
    suraViewModel: SuraViewModel
) {
    Column {
        PlayList(viewModel, reciterViewModel, suraViewModel)
        Spacer(modifier = Modifier.requiredHeight(8.dp))
    }
}

sealed class Screen(val route: String, @StringRes val resourceId: Int, val icon: ImageVector) {
    object Home : Screen("home", R.string.home, Icons.Filled.Home)
    object PlayList : Screen("playList", R.string.play_list, Icons.Filled.List)
}
