package com.quran.audio.app.ui.data

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quran.audio.api.client.model.Reciter
import com.quran.audio.api.client.model.Section
import com.quran.audio.api.client.model.Sura
import com.quran.audio.app.data.DataSource
import kotlinx.coroutines.launch

class MainViewModel(private val dataSource: DataSource) : ViewModel() {
    private val loggerTag = "ReciterViewModel"

    var errorMessage: String by mutableStateOf("")

    private val _reciterList = mutableStateListOf<Reciter>()
    val reciterList: List<Reciter>
        get() = _reciterList

    private val _sectionList = mutableStateListOf<Section>()
    val sectionList: List<Section>
        get() = _sectionList

    private val _suraList = mutableStateListOf<Sura>()
    val suraList: List<Sura>
        get() = _suraList

    fun getReciterList() {
        viewModelScope.launch {
            try {
                dataSource.getReciters().subscribe (
                    {
                        _reciterList.clear()
                        _reciterList.addAll(it)
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

    fun getSectionList() {
        viewModelScope.launch {
            try {
                dataSource.getSections().subscribe (
                    {
                        _sectionList.clear()
                        _sectionList.addAll(it)
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

    fun getSuraList() {
        viewModelScope.launch {
            try {
                dataSource.getSuras().subscribe (
                    {
                        _suraList.clear()
                        _suraList.addAll(it)
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