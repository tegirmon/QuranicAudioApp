package com.quran.audio.app.ui.appbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.dp
import com.quran.audio.app.ui.data.ReciterSelected
import com.quran.audio.app.ui.data.SuraSelected
import com.quran.audio.app.ui.media.PlayerActions
import com.quran.audio.app.ui.media.PlayerControls
import com.quran.audio.app.ui.media.PlayerTitle

@Composable
fun PlayerBottomBar(
    playerActions: PlayerActions,
    reciterSelected: ReciterSelected,
    suraSelected: SuraSelected
) {
    val relativePath = reciterSelected.reciter?.relativePath
    val suraId = suraSelected.sura?.id ?: 0
    val title = if(reciterSelected.reciter == null || suraSelected.sura == null) "Loading.."
                else "${reciterSelected.reciter.name} - ${suraSelected.sura.name.simple}"
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