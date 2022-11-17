package com.quran.audio.app.ui.appbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.dp
import com.quran.audio.app.data.model.CurrentPlaying
import com.quran.audio.app.data.viewmodel.PlayListViewModel
import com.quran.audio.app.ui.media.PlayerActions
import com.quran.audio.app.ui.media.PlayerControls
import com.quran.audio.app.ui.media.PlayerTitle

@Composable
fun PlayerBottomBar(
    playerActions: PlayerActions,
    playListViewModel: PlayListViewModel
) {
    val currentPlaying: CurrentPlaying by playListViewModel.currentPlaying
    val relativePath = currentPlaying.item?.reciter?.relativePath
    val suraId = currentPlaying.item?.suraModel?.id ?: 0
    val title = if(currentPlaying.item == null) "Loading.."
                else "${currentPlaying.item?.reciter?.name} - ${currentPlaying.item?.suraModel?.name}"
    val borderColor = MaterialTheme.colorScheme.tertiary
    val borderWidth = 4.dp
    Card(
        shape = RoundedCornerShape(0.dp),
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .drawBehind {
                val strokeWidth = borderWidth.value * density
                drawLine(
                    borderColor,
                    Offset(0f, 0f),
                    Offset(size.width, 0f),
                    strokeWidth
                )
            }
    ) {
        Column(Modifier.padding(12.dp)) {
            PlayerTitle(title)
            Spacer(Modifier.height(16.dp))
            PlayerControls(playerActions, relativePath, suraId)
        }
    }
}