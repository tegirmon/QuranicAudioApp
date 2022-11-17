package com.quran.audio.app.ui.component.suralist

import androidx.compose.runtime.Composable
import com.quran.audio.api.client.model.Reciter
import com.quran.audio.app.ui.SelectableDropdownBox
import com.quran.audio.app.ui.SelectableItem
import com.quran.audio.app.ui.data.ReciterSelected

@Composable
fun ReciterSelector(
    reciterList: List<Reciter>,
    selectedReciter: ReciterSelected?,
    onSelect: (reciter: Reciter) -> Unit
) {
    if (reciterList.isNotEmpty()) {
        if (selectedReciter?.reciter == null) {
            onSelect(reciterList.first())
        }

        SelectableDropdownBox(
            "Reciter",
            reciterList.map { SelectableItem(it.id, it.name) },
            SelectableItem(selectedReciter!!.reciter!!.id, selectedReciter.reciter!!.name)
        ) { selected ->
            onSelect(reciterList.first { it.id == selected.id })
        }
    }
}
