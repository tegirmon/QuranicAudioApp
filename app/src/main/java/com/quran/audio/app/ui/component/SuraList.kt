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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.quran.audio.api.client.model.Sura
import com.quran.audio.app.ui.navigation.MainActions
import com.quran.audio.app.ui.data.MainViewModel

@ExperimentalMaterialApi
@Composable
fun SuraList(actions: MainActions, viewModel: MainViewModel, reciterRelativePath: String?) {
    LaunchedEffect(Unit, block = {
        viewModel.getSuraList()
    })

    LazyColumn {
        items(viewModel.suraList) { item ->
            SuraRow(item, actions, reciterRelativePath)
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun SuraRow(sura: Sura, actions: MainActions, reciterRelativePath: String?) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(2.dp),
        elevation = 2.dp,
        onClick = {
            actions.mediaPlayerView(reciterRelativePath ?: "", sura.id)
        }
    ) {
        Column(
            modifier = Modifier
                .padding(15.dp)
        ) {
            Text(
                buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.W900,
                            color = MaterialTheme.colors.primary
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
        }
    }
}