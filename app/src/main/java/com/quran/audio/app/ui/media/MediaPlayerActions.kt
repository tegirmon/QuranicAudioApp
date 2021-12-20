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

    override fun playPause(audioUrl: String) {
        if(isPlaying()) {
            player.pause()
        } else {
            Log.d(logTag, audioUrl)
            val mediaItem: MediaItem = MediaItem.Builder()
                .setUri(audioUrl)
                .setMimeType(MimeTypes.AUDIO_MPEG)
                .build()
            player.setMediaItem(mediaItem)
            player.prepare()
            player.play()
        }
        _isPlaying.value = !_isPlaying.value
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
}