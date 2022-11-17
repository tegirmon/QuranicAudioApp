package com.quran.audio.app.data.viewmodel

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.quran.audio.app.data.model.CurrentPlaylist
import com.quran.audio.app.data.model.PlayListItemModel
import com.quran.audio.app.data.model.PlayListModel

class PlayListViewModel(): ViewModel() {
    private val loggerTag = "PlayListViewModel"
    var errorMessage: String by mutableStateOf("")

    private val _playLists = mutableStateMapOf<Int, PlayListModel>()
    val playLists: Map<Int, PlayListModel>
        get() = _playLists

    private val _currentPlayList = mutableStateOf(CurrentPlaylist())
    val currentPlayList: State<CurrentPlaylist>
        get() = _currentPlayList

    fun createPlayList(name: String) {
        val newId = if(_playLists.isEmpty()) 1 else _playLists.values.last().id + 1
        _playLists[newId] = PlayListModel(newId, name, emptyList())
    }

    fun addToPlayList(plId: Int, item: PlayListItemModel) {
        if(_playLists.containsKey(plId)) {
            _playLists[plId]?.let { playListO ->
                _playLists[plId] = PlayListModel(plId, playListO.name, playListO.items + item)
            }
        } else {
            errorMessage = "PlayList doesn't exist"
            Log.d(loggerTag, "addToPlayList: PlayList doesn't exist")
        }
    }

    fun selectPlayList(item: PlayListModel) {
        _currentPlayList.value = CurrentPlaylist(item)
    }
}