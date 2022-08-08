package com.quran.audio.app.ui.component

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.PlayCircleFilled
import androidx.compose.material.icons.filled.PlayCircleOutline
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.quran.audio.api.client.model.Sura
import com.quran.audio.api.client.model.SuraName

@Composable
fun SuraList(
    suraList: List<Sura>,
    selectSura: (sura: Sura) -> Unit,
    addToPlaylist: (sura: Sura) -> Unit
) {
    val context = LocalContext.current
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 8.dp)
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

@Composable
fun SuraRow(
    sura: Sura,
    onSelect: (Sura) -> Unit,
    onAdd: (Sura) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp,

    ) {
        Column(
            modifier = Modifier
                .padding(15.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Column {
                    SuraName(sura.name)
                }
                Column {
                    SuraActions(onSelect = { onSelect(sura) }, onAdd = { onAdd(sura) })
                }
            }
        }
    }
}

@Composable
fun SuraName(name: SuraName) {
    Text(
        buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.W700,
                    color = MaterialTheme.colors.secondaryVariant
                )
            ) {
                append(name.simple)
            }
        }
    )
    Text(
        buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.W400,
                    color = MaterialTheme.colors.secondary
                )
            ) {
                append(name.arabic)
            }
        }
    )
}

@Composable
fun SuraActions(onSelect: () -> Unit, onAdd: () -> Unit) {
    Row(modifier = Modifier
        .fillMaxWidth(),
        horizontalArrangement = Arrangement.End,
    ) {
        PlayButton(modifier = Modifier, color = MaterialTheme.colors.primary) {
            onSelect()
        }
        AddToButton(modifier = Modifier, color = MaterialTheme.colors.secondary) {
            onAdd()
        }
    }
}

@Composable
fun PlayButton(
    modifier: Modifier = Modifier,
    color: Color = Color.White,
    onClick: () -> Unit
) {
    ToggleableIconButton(
        modifier = modifier,
        color = color,
        Icons.Filled.PlayCircleFilled,
        Icons.Default.PlayCircleOutline
    ) {
        onClick()
    }
}

@Composable
fun AddToButton(
    modifier: Modifier = Modifier,
    color: Color = Color.White,
    onClick: () -> Unit
) {
    ToggleableIconButton(
        modifier = modifier,
        color = color,
        Icons.Filled.AddCircle,
        Icons.Default.AddCircleOutline
    ) {
        onClick()
    }
}
