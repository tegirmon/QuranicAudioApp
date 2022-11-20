package com.quran.audio.app.ui.component.suralist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.quran.audio.app.data.model.PlayListModel
import com.quran.audio.app.ui.component.common.AddToPlayListContainer
import com.quran.audio.app.ui.component.common.PlayButton

@Composable
fun SuraActions(playLists: List<PlayListModel>, onPlay: () -> Unit, addTo: (PlayListModel) -> Unit) {
    Row(modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End,
    ) {
        PlayButton { onPlay() }
        AddToPlayListContainer(playLists = playLists) { playList -> addTo(playList) }
    }
}
