package com.quran.audio.app.ui.sura

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
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

@Composable
fun SuraList(viewModel: SuraViewModel) {
    LaunchedEffect(Unit, block = {
        viewModel.getSuraList()
    })

    LazyColumn {
        items(viewModel.suraList) { item ->
            SuraRow(item)
        }
    }
}

@Composable
fun SuraRow(sura: Sura) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(2.dp),
        elevation = 2.dp
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