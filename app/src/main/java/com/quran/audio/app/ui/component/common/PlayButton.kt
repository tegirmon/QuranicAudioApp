package com.quran.audio.app.ui.component.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayCircleFilled
import androidx.compose.material.icons.filled.PlayCircleOutline
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun PlayButton(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary,
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
