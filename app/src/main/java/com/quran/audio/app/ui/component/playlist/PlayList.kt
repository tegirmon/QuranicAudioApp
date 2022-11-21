package com.quran.audio.app.ui.component.playlist

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlaylistPlay
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.quran.audio.app.data.model.CurrentPlaylist
import com.quran.audio.app.data.viewmodel.PlayListViewModel
import com.quran.audio.app.ui.media.AudioUriParser
import com.quran.audio.app.ui.media.MediaPlayerActions

@ExperimentalMaterialApi
@Composable
fun PlayList(
    playListViewModel: PlayListViewModel,
    mediaPlayerActions: MediaPlayerActions
) {
    val currentPlayList: CurrentPlaylist by playListViewModel.currentPlayList
    PlayListSelector(
        playListViewModel.playLists.values.toList(),
        currentPlayList.playList
    ) { playList ->
        playListViewModel.selectPlayList(playList)
    }
    currentPlayList.playList?.let { playList ->
        LazyColumn {
            item {
                if(playList.items.isEmpty()) {
                    Text(text = "Playlist is empty")
                }
            }
            items(playList.items.sortedBy { it.order }) { item ->
                PlayListRow(item) { selectedPlayListItem ->
                    playListViewModel.playNow(selectedPlayListItem)
                    playListViewModel.currentPlayList.value.playList?.let { playList ->
                        mediaPlayerActions.addToPlaylist(playList.items.map { ii ->
                            AudioUriParser.parse(ii.reciter.relativePath, ii.sura.id)
                        })

                    }
                    mediaPlayerActions.play(selectedPlayListItem.order - 1)
                }
            }
        }
        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(Icons.Default.PlaylistPlay, "Play All")
            Text("Play All")
        }
    }
}
