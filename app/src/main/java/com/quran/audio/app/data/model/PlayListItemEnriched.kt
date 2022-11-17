package com.quran.audio.app.data.model

data class PlayListItemEnriched(
    val playListId: Int,
    val suraModel: SuraModel,
    val reciter: ReciterModel,
    val order: Int
)
