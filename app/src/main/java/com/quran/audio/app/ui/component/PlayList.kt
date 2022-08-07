package com.quran.audio.app.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.quran.audio.app.ui.data.MainViewModel
import com.quran.audio.app.ui.data.PlayListItem

@ExperimentalMaterialApi
@Composable
fun PlayList(viewModel: MainViewModel) {
    LazyColumn {
        item {
            if(viewModel.playList.isEmpty()) {
                Text(text = "Playlist is empty")
            }
        }
        items(viewModel.playList) { item ->
            PlayListRow(item,
                onPlayListItemClicked = {

                }
            )
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun PlayListRow(playListItem: PlayListItem, onPlayListItemClicked: (PlayListItem) -> Unit) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp,
        onClick = { onPlayListItemClicked(playListItem) },
        backgroundColor = MaterialTheme.colors.surface,
        contentColor = MaterialTheme.colors.onSurface
    ) {
        Column(
            modifier = Modifier
                .padding(15.dp)
        ) {
            Text(
                buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(fontWeight = FontWeight.W700, color = MaterialTheme.colors.secondaryVariant)
                    ) {
                        append(playListItem.title)
                        append("(${playListItem.order})")
                    }
                }
            )
        }
    }
}