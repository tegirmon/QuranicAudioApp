package com.quran.audio.app.data.viewmodel

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.quran.audio.app.data.model.*

class PlayListViewModel(): ViewModel() {
    private val loggerTag = "PlayListViewModel"
    var errorMessage: String by mutableStateOf("")

    private val _playLists = mutableStateMapOf<Int, PlayListModel>()
    val playLists: Map<Int, PlayListModel>
        get() = _playLists

    private val _currentPlayList = mutableStateOf(CurrentPlaylist())
    val currentPlayList: State<CurrentPlaylist>
        get() = _currentPlayList

    private val _currentPlaying = mutableStateOf(CurrentPlaying())
    val currentPlaying: State<CurrentPlaying>
        get() = _currentPlaying

    fun createPlayList(name: String) {
        val newId = if(_playLists.isEmpty()) 1 else _playLists.values.last().id + 1
        _playLists[newId] = PlayListModel(newId, name, emptyList())
    }

    fun addToPlayList(plId: Int, sura: SuraModel, reciter: ReciterModel) {
        if(playLists.containsKey(plId)) {
            playLists[plId]?.let { playList ->
                val lastOrder = if(playList.items.isEmpty()) 0 else playList.items.maxWith(Comparator.comparingInt { it.order }).order
                val item = PlayListItemEnriched(plId, sura, reciter, lastOrder + 1)
                _playLists[plId] = PlayListModel(plId, playList.name, playList.items + item)
            }
        } else {
            errorMessage = "PlayList doesn't exist"
            Log.d(loggerTag, "addToPlayList: PlayList doesn't exist")
        }
    }

    fun selectPlayList(item: PlayListModel) {
        _currentPlayList.value = CurrentPlaylist(item)
    }

    fun playNow(item: PlayListItemEnriched) {
        _currentPlaying.value = CurrentPlaying(item)
    }
}