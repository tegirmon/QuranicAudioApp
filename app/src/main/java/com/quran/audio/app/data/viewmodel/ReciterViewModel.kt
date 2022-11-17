package com.quran.audio.app.data.viewmodel

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quran.audio.app.data.DataSource
import com.quran.audio.app.data.model.CurrentReciter
import com.quran.audio.app.data.model.ReciterModel
import kotlinx.coroutines.launch

class ReciterViewModel(private val dataSource: DataSource): ViewModel() {
    private val loggerTag = "ReciterViewModel"
    var errorMessage: String by mutableStateOf("")

    private val _reciterList = mutableStateListOf<ReciterModel>()
    val reciterList: List<ReciterModel>
        get() = _reciterList

    private val _currentReciter = mutableStateOf(CurrentReciter())
    val currentReciter: State<CurrentReciter>
        get() = _currentReciter

    fun getReciterList() {
        viewModelScope.launch {
            errorMessage = ""
            try {
                dataSource.getReciters().subscribe (
                    {
                        _reciterList.clear()
                        _reciterList.addAll(it.map { r -> ReciterModel(r.id, r.name, r.relativePath, r.fileFormats, r.sectionId, r.description ?: "") })
                        Log.e(loggerTag, it.size.toString())
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

    fun getReciter(id: Int): ReciterModel = reciterList.first { it.id == id }

    fun selectReciter(reciter: ReciterModel) {
        _currentReciter.value = CurrentReciter(reciter)
    }
}