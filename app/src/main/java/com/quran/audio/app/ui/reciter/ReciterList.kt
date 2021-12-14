package com.quran.audio.app.ui.reciter

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.quran.audio.api.client.model.Reciter

@Composable
fun ReciterList(viewModel: ReciterViewModel, sectionId: Int) {
    LaunchedEffect(Unit, block = {
        viewModel.getReciterList()
    })

    LazyColumn {
        items(viewModel.reciterList.filter { it.sectionId == sectionId }) { item ->
            ReciterRow(item)
        }
    }
}

@Composable
fun ReciterRow(reciter: Reciter) {
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
                        style = SpanStyle(fontWeight = FontWeight.W900, color = MaterialTheme.colors.primary)
                    ) {
                        append(reciter.name)
                    }
                }
            )
            Text(buildAnnotatedString {
                withStyle(
                    style = SpanStyle(fontWeight = FontWeight.W400, color = MaterialTheme.colors.secondary)
                ) {
                    append(reciter.arabicName ?: "")
                }
            })
            Text(reciter.description ?: "")
        }
    }
}