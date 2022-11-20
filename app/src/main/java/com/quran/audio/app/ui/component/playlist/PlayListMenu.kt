package com.quran.audio.app.ui.component.playlist

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.PopupProperties
import com.quran.audio.app.data.model.PlayListModel

@Composable
fun PlayListMenu(
    menuExpanded: Boolean,
    playLists: List<PlayListModel>,
    dismiss: () -> Unit,
    makeSelection: (playList: PlayListModel) -> Unit
) {
    DropdownMenu(
        expanded = menuExpanded,
        properties = PopupProperties(),
        onDismissRequest = { dismiss() },
        modifier = Modifier.fillMaxWidth()
    ) {
        playLists.forEach { playList ->
            DropdownMenuItem(
                onClick = {
                    makeSelection(playList)
                    dismiss()
                },
                text = { Text(text = playList.name) }
            )
        }
    }
}
