package com.quran.audio.app.ui.media

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.util.MimeTypes

class MediaPlayerActions(private val player: ExoPlayer) : PlayerActions {
    private val logTag = "MediaPlayerActions"
    private val _isPlaying = mutableStateOf(false)

    override fun isPlaying(): Boolean {
        return _isPlaying.value
    }

    override fun stop() {
        player.stop()
        _isPlaying.value = false
    }

    override fun addToPlaylist(audioUrlList: List<String>) {
        player.setMediaItems(audioUrlList.map(this::mediaItemFromAudioUrl))
        player.prepare()
    }

    override fun playPause() {
        if(isPlaying()) {
            player.pause()
        } else {
            player.play()
        }
        _isPlaying.value = !isPlaying()
    }

    override fun play(index: Int) {
        player.seekTo(index, 0L)
        player.play()
        _isPlaying.value = true
    }

    override fun forward10() {
        Log.d(logTag, "forward")
        if(isPlaying()) {
            player.seekForward()
        }
    }

    override fun replay10() {
        Log.d(logTag, "replay")
        if(isPlaying()) {
            player.seekBack()
        }
    }

    override fun skipNext() {
        Log.d(logTag, "next")
        if(isPlaying()) {
            player.seekToNext()
        }
    }

    override fun skipPrevious() {
        Log.d(logTag, "previous")
        if(isPlaying()) {
            player.seekToPrevious()
        }
    }

    private fun mediaItemFromAudioUrl(audioUrl: String): MediaItem = MediaItem.Builder().setUri(audioUrl).setMimeType(MimeTypes.AUDIO_MPEG).build()
}