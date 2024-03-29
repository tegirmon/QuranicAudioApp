package com.quran.audio.app.ui.component.common

import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun ToggleableIconButton(
    modifier: Modifier = Modifier,
    color: Color = Color.White,
    toggledIcon: ImageVector,
    otherIcon: ImageVector,
    initialState: Boolean = false,
    onClick: () -> Unit
) {
    var toggled by remember { mutableStateOf(initialState) }

    IconToggleButton(
        modifier = modifier,
        checked = toggled,
        onCheckedChange = {
            toggled = !toggled
            onClick()
        }
    ) {
        Icon(
            tint = color,
            imageVector = if (toggled) {
                toggledIcon
            } else {
                otherIcon
            },
            contentDescription = null
        )
    }
}
