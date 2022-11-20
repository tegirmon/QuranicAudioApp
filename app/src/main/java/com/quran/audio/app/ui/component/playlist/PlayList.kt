package com.quran.audio.app.ui.component.playlist

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.quran.audio.app.data.model.CurrentPlaylist
import com.quran.audio.app.data.model.PlayListItemEnriched
import com.quran.audio.app.data.viewmodel.PlayListViewModel
import com.quran.audio.app.data.viewmodel.ReciterViewModel
import com.quran.audio.app.data.viewmodel.SuraViewModel

@ExperimentalMaterialApi
@Composable
fun PlayList(
    playListViewModel: PlayListViewModel,
    reciterViewModel: ReciterViewModel,
    suraViewModel: SuraViewModel
) {
    val currentPlayList: CurrentPlaylist by playListViewModel.currentPlayList
    PlayListSelector(
        playListViewModel.playLists.values.toList(),
        currentPlayList.item
    ) { playList ->
        playListViewModel.selectPlayList(playList)
    }
    currentPlayList.item?.let { playList ->
        LazyColumn {
            item {
                if(playList.items.isEmpty()) {
                    Text(text = "Playlist is empty")
                }
            }
            items(playList.items.sortedBy { it.order }) { item ->
                val reciter = reciterViewModel.getReciter(item.reciterId)
                val sura = suraViewModel.getSura(item.suraId)
                PlayListRow(
                    PlayListItemEnriched(item.playListId, sura, reciter, item.order)
                ) {
                    playListViewModel.playNow(it)
                }
            }
        }
    }
}
