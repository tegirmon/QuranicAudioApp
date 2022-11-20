package com.quran.audio.app.ui.component.suralist

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.quran.audio.app.data.model.PlayListModel
import com.quran.audio.app.data.model.SuraModel
import com.quran.audio.app.ui.component.common.CustomListItem

@Composable
fun SuraRow(
    sura: SuraModel,
    onPlay: (SuraModel) -> Unit,
    onAddTo: (PlayListModel, SuraModel) -> Unit,
    playLists: List<PlayListModel>
) {
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
            Row(modifier = Modifier.fillMaxWidth()) {
                Column {
                    CustomListItem(sura.name, sura.name)
                }
                Column {
                    SuraActions(playLists, { onPlay(sura) }) { playList ->
                        onAddTo(playList, sura)
                    }
                }
            }
        }
    }
}
