package com.quran.audio.app.ui.component.common

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle

@Composable
fun CustomListItem(title: String, subTitle: String) {
    Text(
        buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.W700,
                    color = MaterialTheme.colorScheme.tertiary
                )
            ) {
                append(title)
            }
        }
    )
    Text(
        buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.W400,
                    color = MaterialTheme.colorScheme.secondary
                )
            ) {
                append(subTitle)
            }
        }
    )
}
