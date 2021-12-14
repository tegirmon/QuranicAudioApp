package com.quran.audio.app.ui.reciter

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quran.audio.api.client.model.Reciter
import com.quran.audio.app.data.DataSource
import kotlinx.coroutines.launch

class ReciterViewModel(private val dataSource: DataSource) : ViewModel() {
    private val loggerTag = "ReciterViewModel"

    private val _list = mutableStateListOf<Reciter>()
    var errorMessage: String by mutableStateOf("")
    val reciterList: List<Reciter>
        get() = _list

    fun getReciterList() {
        viewModelScope.launch {
            try {
                dataSource.getReciters().subscribe (
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