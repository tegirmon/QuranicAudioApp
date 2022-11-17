package com.quran.audio.app.ui.component.suralist

import androidx.compose.runtime.Composable
import com.quran.audio.app.data.model.CurrentReciter
import com.quran.audio.app.data.model.ReciterModel
import com.quran.audio.app.ui.SelectableDropdownBox
import com.quran.audio.app.ui.SelectableItem

@Composable
fun ReciterSelector(
    reciterList: List<ReciterModel>,
    currentReciter: CurrentReciter?,
    onSelect: (reciter: ReciterModel) -> Unit
) {
    if (reciterList.isNotEmpty()) {
        val selectedItem = if (currentReciter?.reciter == null) {
            onSelect(reciterList.first())
            SelectableItem(reciterList.first().id, reciterList.first().name)
        } else {
            SelectableItem(currentReciter.reciter.id, currentReciter.reciter.name)
        }

        SelectableDropdownBox(
            "Reciter",
            reciterList.map { SelectableItem(it.id, it.name) },
            selectedItem
        ) { selected ->
            onSelect(reciterList.first { it.id == selected.id })
        }
    }
}
