package com.quran.audio.app.ui

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.quran.audio.app.ui.theme.QuranicAudioAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectableDropdownBox(
    label: String,
    options: List<SelectableItem>,
    selectedValue: SelectableItem,
    onSelection: (selected: SelectableItem) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(selectedValue.label) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        TextField(
            readOnly = true,
            value = selectedOptionText,
            onValueChange = { },
            label = { Text(label) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
            modifier = Modifier.fillMaxWidth().menuAnchor()
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            },
            modifier = Modifier.exposedDropdownSize()
        ) {
            options.forEach { selectionOption ->
                DropdownMenuItem(
                    onClick = {
                        selectedOptionText = selectionOption.label
                        expanded = false
                        onSelection(selectionOption)
                    },
                    text = { Text(text = selectionOption.label) }
                )
            }
        }
    }
}

data class SelectableItem(val id: Int, val label: String)

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    QuranicAudioAppTheme {
        SelectableDropdownBox(
            "Label",
            listOf(SelectableItem(1, "A"), SelectableItem(2, "B")),
            SelectableItem(1, "A")
        ) {
            Log.d("DefaultPreview", it.label)
        }
    }
}