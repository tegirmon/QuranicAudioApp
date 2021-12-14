package com.quran.audio.app.ui.section

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quran.audio.api.client.model.Section
import com.quran.audio.app.data.DataSource
import kotlinx.coroutines.launch

class SectionViewModel(private val dataSource: DataSource) : ViewModel() {
    private val loggerTag = "SectionViewModel"

    private val _list = mutableStateListOf<Section>()
    var errorMessage: String by mutableStateOf("")
    val sectionList: List<Section>
        get() = _list

    fun getSuraList() {
        viewModelScope.launch {
            try {
                dataSource.getSections().subscribe (
                    {
                        _list.clear()
                        _list.addAll(it)
                    }, {
                        errorMessage = it.localizedMessage ?: "Failed "
                        Log.e(loggerTag, errorMessage)
                    }
                        )
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }
}