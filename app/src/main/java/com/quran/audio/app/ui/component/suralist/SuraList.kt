package com.quran.audio.app.ui.component.suralist

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.quran.audio.app.data.model.PlayListModel
import com.quran.audio.app.data.model.SuraModel

@Composable
fun SuraList(
    suraList: List<SuraModel>,
    onPlay: (SuraModel) -> Unit,
    addToPlaylist: (PlayListModel, SuraModel) -> Unit,
    playLists: List<PlayListModel>
) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 0.dp)
    ) {
        items(suraList) { sura ->
            SuraRow(
                sura,
                onPlay = { sura1 -> onPlay(sura1) },
                onAddTo = { playList, sura1 -> addToPlaylist(playList, sura1) },
                playLists
            )
        }
    }
}
