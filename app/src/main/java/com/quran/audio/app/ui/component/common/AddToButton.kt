package com.quran.audio.app.ui.component.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun AddToButton(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.secondary,
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
