package com.quran.audio.app.data.model

data class PlayListItemEnriched(
    val playListId: Int,
    val sura: SuraModel,
    val reciter: ReciterModel,
    val order: Int
)
