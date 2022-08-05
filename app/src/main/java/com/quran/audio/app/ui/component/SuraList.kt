package com.quran.audio.app.ui.component

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.quran.audio.api.client.model.Sura
import com.quran.audio.app.ui.data.MainViewModel

@ExperimentalMaterialApi
@Composable
fun SuraList(viewModel: MainViewModel) {
    val context = LocalContext.current
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(viewModel.suraList) { item ->
            val reciterSelected = viewModel.selectedReciter.value
            val title = "${reciterSelected?.reciter?.name} - ${item.name.simple}"
            SuraRow(item,
                onSuraSelected = {
                    viewModel.selectSura(it)
                    viewModel.addToPlaylist(
                        title,
                        reciterSelected?.reciter!!,
                        it,
                        0
                    )
                    Toast.makeText(context, "$title has been added to playlist", Toast.LENGTH_SHORT)
                        .show()
                }
            )
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun SuraRow(sura: Sura, onSuraSelected: (Sura) -> Unit) {
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
            Text(
                buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.W700,
                            color = MaterialTheme.colors.secondaryVariant
                        )
                    ) {
                        append(sura.name.simple)
                    }
                }
            )
            Text(buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.W400,
                        color = MaterialTheme.colors.secondary
                    )
                ) {
                    append(sura.name.arabic)
                }
            })
            Text(sura.name.complex)
            Text(sura.revelation.place)
            Button(onClick = { onSuraSelected(sura) }) {
                Text(text = "Add to playlist")
            }
        }
    }
}
