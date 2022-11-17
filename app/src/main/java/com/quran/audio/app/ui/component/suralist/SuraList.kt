package com.quran.audio.app.ui.component.suralist

import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.quran.audio.api.client.model.Sura

@Composable
fun SuraList(
    suraList: List<Sura>,
    selectSura: (sura: Sura) -> Unit,
    addToPlaylist: (sura: Sura) -> Unit
) {
    val context = LocalContext.current
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 0.dp)
    ) {
        items(suraList) { item ->
            SuraRow(item,
                onSelect = {
                    selectSura(it)
                },
                onAdd = {
                    addToPlaylist(it)
                    Toast.makeText(context, "Added to playlist", Toast.LENGTH_SHORT)
                        .show()
                }
            )
        }
    }
}
