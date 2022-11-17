package com.quran.audio.app.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
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
            items(playList.items) { item ->
                val reciter = reciterViewModel.getReciter(item.reciterId)
                val sura = suraViewModel.getSura(item.suraId)
                PlayListRow(PlayListItemEnriched(item.playListId, sura, reciter, item.order))
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun PlayListRow(playListItem: PlayListItemEnriched) {
    Card(
        modifier = Modifier
            .padding(2.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(0.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(15.dp)
        ) {
            Text(
                buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.W700,
                            color = MaterialTheme.colorScheme.tertiary
                        )
                    ) {
                        append("${playListItem.order}. ")
                        append(playListItem.suraModel.name)
                    }
                }
            )
            Text(
                buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.W400,
                            color = MaterialTheme.colorScheme.secondary
                        )
                    ) {
                        append(playListItem.reciter.name)
                    }
                }
            )
        }
    }
}
