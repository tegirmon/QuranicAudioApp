package com.quran.audio.app.ui.component.suralist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.quran.audio.app.ui.component.common.AddToButton
import com.quran.audio.app.ui.component.common.PlayButton

@Composable
fun SuraActions(onSelect: () -> Unit, onAdd: () -> Unit) {
    Row(modifier = Modifier
        .fillMaxWidth(),
        horizontalArrangement = Arrangement.End,
    ) {
        PlayButton { onSelect() }
        AddToButton { onAdd() }
    }
}
