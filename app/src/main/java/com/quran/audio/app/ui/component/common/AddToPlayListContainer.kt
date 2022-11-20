package com.quran.audio.app.ui.component.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.quran.audio.app.data.model.PlayListModel
import com.quran.audio.app.ui.component.playlist.PlayListMenu

@Composable
fun AddToPlayListContainer(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.secondary,
    playLists: List<PlayListModel>,
    selectPlayList: (PlayListModel) -> Unit
) {
    var menuExpanded by remember { mutableStateOf(false) }
    ToggleableIconButton(
        modifier = modifier,
        color = color,
        Icons.Filled.AddCircle,
        Icons.Default.AddCircleOutline
    ) {
        menuExpanded = true
    }
    PlayListMenu(menuExpanded, playLists, { menuExpanded = false }) { playList ->
        selectPlayList(playList)
    }
}
