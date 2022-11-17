package com.quran.audio.app.ui.component.suralist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.quran.audio.api.client.model.Sura
import com.quran.audio.app.ui.component.common.CustomListItem

@Composable
fun SuraRow(
    sura: Sura,
    onSelect: (Sura) -> Unit,
    onAdd: (Sura) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(2.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(0.dp)

    ) {
        Column(
            modifier = Modifier
                .padding(15.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Column {
                    CustomListItem(sura.name.simple, sura.name.arabic)
                }
                Column {
                    SuraActions(onSelect = { onSelect(sura) }, onAdd = { onAdd(sura) })
                }
            }
        }
    }
}
