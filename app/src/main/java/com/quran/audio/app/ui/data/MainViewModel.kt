package com.quran.audio.app.ui.data

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quran.audio.api.client.model.Reciter
import com.quran.audio.api.client.model.Section
import com.quran.audio.api.client.model.Sura
import com.quran.audio.app.data.DataSource
import com.quran.audio.app.ui.media.MediaPlayerActions
import kotlinx.coroutines.launch

class MainViewModel(private val dataSource: DataSource, val mediaPlayerActions: MediaPlayerActions) : ViewModel() {
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

    private var _selectedReciter: MutableLiveData<ReciterSelected> = MutableLiveData(ReciterSelected())
    val selectedReciter: LiveData<ReciterSelected> = _selectedReciter

    private var _selectedSura: MutableLiveData<SuraSelected> = MutableLiveData(SuraSelected())
    val selectedSura: LiveData<SuraSelected> = _selectedSura


    private val _playlist = mutableStateListOf<PlayListItem>()
    val playList: List<PlayListItem> = _playlist

    fun addToPlaylist(item: String, reciter: Reciter, sura: Sura, i: Int) {
        _playlist.add(PlayListItem(item, reciter, sura, i))
    }

    fun selectReciter(reciter: Reciter) {
        _selectedReciter.value = ReciterSelected(reciter)
        mediaPlayerActions.stop()
    }

    fun selectSura(sura: Sura) {
        _selectedSura.value = SuraSelected(sura)
        mediaPlayerActions.stop()
    }

    fun getReciterList() {
        viewModelScope.launch {
            try {
                dataSource.getReciters().subscribe (
                    {
                        _reciterList.clear()
                        _reciterList.addAll(it)
                        selectReciter(it.first())
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
                        selectSura(it.first())
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