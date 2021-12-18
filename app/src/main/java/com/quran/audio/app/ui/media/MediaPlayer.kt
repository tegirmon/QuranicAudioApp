package com.quran.audio.app.ui.media

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun MediaPlayer(
    title: String,
    playerActions: PlayerActions,
    @DrawableRes img: Int,
    reciterRelativePath: String?,
    suraId: Int
) {
    Column(
        Modifier
            .background(MaterialTheme.colors.background)
            .padding(12.dp)
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        PlayerImage(img)
        Spacer(Modifier.height(32.dp))
        PlayerTitle(title)
        Spacer(Modifier.height(16.dp))
        PlayerControls(playerActions, reciterRelativePath, suraId)
    }
}

@Composable
fun PlayerImage(@DrawableRes image: Int) {
    Column(Modifier.fillMaxWidth()) {
        Row(Modifier.align(Alignment.CenterHorizontally)) {
            Card(
                modifier = Modifier
                    .size(360.dp)
                    .fillMaxWidth(),
                shape = CircleShape,
                elevation = 2.dp
            ) {
                Image(
                    painterResource(image),
                    contentDescription = "Art",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}

@Composable
fun PlayerTitle(title: String) {
    Column(Modifier.fillMaxWidth()) {
        Row(Modifier.align(Alignment.CenterHorizontally)) {
            Text(
                title,
                Modifier.fillMaxWidth(),
                MaterialTheme.colors.secondaryVariant,
                textAlign = TextAlign.Center
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
                    MaterialTheme.colors.secondary
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
                    MaterialTheme.colors.secondary
                )
            }
            IconButton(
                onClick = { playerActions.playPause(reciterRelativePath, suraId) },
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
                    MaterialTheme.colors.secondary
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
                    MaterialTheme.colors.secondary
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
                    MaterialTheme.colors.secondary
                )
            }
        }
    }
}

interface PlayerActions {
    fun isPlaying(): Boolean
    fun playPause(reciterRelativePath: String?, suraId: Int)
    fun forward10()
    fun replay10()
    fun skipNext()
    fun skipPrevious()
}
