package com.quran.audio.app.ui.media

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp


@Composable
fun PlayerTitle(title: String) {
    Column(Modifier.fillMaxWidth()) {
        Row(Modifier.align(Alignment.CenterHorizontally)) {
            Text(
                title,
                Modifier.fillMaxWidth(),
                MaterialTheme.colorScheme.tertiary,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.W600
            )
        }
    }
}

@Composable
fun PlayerControls(playerActions: PlayerActions, reciterRelativePath: String?, suraId: Int) {
    val playButtonSize = 64.dp
    val buttonSize = 48.dp
    Column(Modifier.fillMaxWidth()) {
        Row(Modifier.align(Alignment.CenterHorizontally)) {
            IconButton(
                onClick = { playerActions.skipPrevious() },
                Modifier
                    .size(buttonSize)
                    .align(Alignment.Bottom),
            ) {
                Icon(
                    Icons.Rounded.SkipPrevious,
                    "Previous",
                    Modifier.fillMaxSize(),
                    MaterialTheme.colorScheme.secondary
                )
            }
            IconButton(
                onClick = { playerActions.replay10() },
                Modifier
                    .size(buttonSize)
                    .align(Alignment.Bottom),
            ) {
                Icon(
                    Icons.Rounded.Replay10, "",
                    Modifier.fillMaxSize(),
                    MaterialTheme.colorScheme.secondary
                )
            }
            IconButton(
                onClick = { playerActions.playPause(AudioUriParser.parse(reciterRelativePath, suraId)) },
                Modifier
                    .size(playButtonSize)
                    .align(Alignment.Bottom),
            ) {
                Icon(
                    if (playerActions.isPlaying()) {
                        Icons.Rounded.PauseCircle
                    } else {
                        Icons.Rounded.PlayCircle
                    }, "",
                    Modifier.fillMaxSize(),
                    MaterialTheme.colorScheme.secondary
                )
            }
            IconButton(
                onClick = { playerActions.forward10() },
                Modifier
                    .size(buttonSize)
                    .align(Alignment.Bottom),
            ) {
                Icon(
                    Icons.Rounded.Forward10, "",
                    Modifier.fillMaxSize(),
                    MaterialTheme.colorScheme.secondary
                )
            }
            IconButton(
                onClick = { playerActions.skipNext() },
                Modifier
                    .size(buttonSize)
                    .align(Alignment.Bottom),
            ) {
                Icon(
                    Icons.Rounded.SkipNext, "",
                    Modifier.fillMaxSize(),
                    MaterialTheme.colorScheme.secondary
                )
            }
        }
    }
}

interface PlayerActions {
    fun isPlaying(): Boolean
    fun playPause(audioUrl: String)
    fun forward10()
    fun replay10()
    fun skipNext()
    fun skipPrevious()
    fun stop()
}
