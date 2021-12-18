package com.quran.audio.app.ui.media

object AudioUriParser {
    fun parse(reciterRelativePath: String?, suraId: Int): String {
        val suraIdPath = zeroPad(suraId)
        var audioUrl = "https://download.quranicaudio.com/quran/"
        audioUrl += if(!reciterRelativePath.isNullOrBlank() && reciterRelativePath.last() == '/') "$reciterRelativePath" else "$reciterRelativePath/"
        audioUrl += suraIdPath
        audioUrl += ".mp3"
        return audioUrl
    }

    private fun zeroPad(suraId: Int): String {
        return when {
            suraId < 10 -> {
                "00$suraId"
            }
            suraId < 100 -> {
                "0$suraId"
            }
            else -> {
                "$suraId"
            }
        }
    }
}