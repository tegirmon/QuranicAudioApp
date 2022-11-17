package com.quran.audio.app.ui.component

import androidx.compose.runtime.Composable
import com.quran.audio.app.data.model.PlayListModel
import com.quran.audio.app.ui.SelectableDropdownBox
import com.quran.audio.app.ui.SelectableItem

@Composable
fun PlayListSelector(
    playlists: List<PlayListModel>,
    selectedPlayList: PlayListModel?,
    onSelect: (playList: PlayListModel) -> Unit
) {
    if (playlists.isNotEmpty()) {
        val selectedItem = if (selectedPlayList == null) {
            onSelect(playlists.first())
            SelectableItem(playlists.first().id, playlists.first().name)
        } else {
            SelectableItem(selectedPlayList.id, selectedPlayList.name)
        }

        SelectableDropdownBox(
            "Playlist",
            playlists.map { SelectableItem(it.id, it.name) },
            selectedItem
        ) { selected ->
            onSelect(playlists.first { it.id == selected.id })
        }
    }
}
