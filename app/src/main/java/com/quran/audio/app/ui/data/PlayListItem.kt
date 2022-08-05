package com.quran.audio.app.ui.data

import com.quran.audio.api.client.model.Reciter
import com.quran.audio.api.client.model.Sura

data class PlayListItem(
    val title: String,
    val reciter: Reciter,
    val sura: Sura,
    val order: Int
)