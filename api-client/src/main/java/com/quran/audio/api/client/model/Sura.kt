package com.quran.audio.api.client.model

import com.google.gson.annotations.SerializedName

data class Sura(
    val id: Int,
    val page: List<Int>,
    @SerializedName("bismillah_pre")
    val displayBismillah: Boolean,
    @SerializedName("ayat")
    val ayatCount: Int,
    val name: SuraName,
    val revelation: Revelation

)

data class SuraName(
    val complex: String,
    val simple: String,
    val english: String,
    val arabic: String
)

data class Revelation(
    val place: String,
    val order: Int
)
