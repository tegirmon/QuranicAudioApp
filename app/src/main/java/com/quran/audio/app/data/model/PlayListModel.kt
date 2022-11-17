package com.quran.audio.app.data.model

data class PlayListModel(
    val id: Int,
    val name: String,
    val items: List<PlayListItemModel>
)
