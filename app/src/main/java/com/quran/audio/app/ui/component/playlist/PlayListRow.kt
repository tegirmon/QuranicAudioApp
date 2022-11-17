package com.quran.audio.app.ui.component.playlist

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.quran.audio.app.data.model.PlayListItemEnriched
import com.quran.audio.app.ui.component.common.CustomListItem
import com.quran.audio.app.ui.component.common.PlayButton

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
            Row(modifier = Modifier.fillMaxWidth()) {
                Column {
                    CustomListItem(
                        "${playListItem.order}. ${playListItem.suraModel.name}",
                        playListItem.reciter.name
                    )
                }
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End,
                    ) {
                        PlayButton {}
                    }
                }
            }
        }
    }
}
