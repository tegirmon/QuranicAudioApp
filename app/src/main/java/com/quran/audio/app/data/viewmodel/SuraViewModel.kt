package com.quran.audio.app.data.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quran.audio.app.data.DataSource
import com.quran.audio.app.data.model.SuraModel
import kotlinx.coroutines.launch

class SuraViewModel(private val dataSource: DataSource): ViewModel() {
    private val loggerTag = "SuraViewModel"
    var errorMessage: String by mutableStateOf("")
    private val _suraList = mutableStateListOf<SuraModel>()
    val suraList: List<SuraModel>
        get() = _suraList

    fun getSuraList() {
        viewModelScope.launch {
            errorMessage = ""
            try {
                dataSource.getSuras().subscribe (
                    {
                        _suraList.clear()
                        _suraList.addAll(it.map { sura -> SuraModel(sura.id, sura.name.simple, sura.ayatCount) })
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

    fun getSura(id: Int): SuraModel = suraList.first { it.id == id }
}