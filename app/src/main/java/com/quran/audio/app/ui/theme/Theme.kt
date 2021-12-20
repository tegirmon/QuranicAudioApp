package com.quran.audio.app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
    primary = LessBlack,
    primaryVariant = AlmostBlack,
    secondary = BrightBlueLight,
    secondaryVariant = BrightBlue,
    onPrimary = BlueGrayLight
)

private val LightColorPalette = lightColors(
    primary = BlueGray,
    primaryVariant = BlueGrayDark,
    secondary = BrightBlue,
    secondaryVariant = BrightBlueDark,
    background = White,
    surface = AlmostWhite,
    onPrimary = White,
    onSecondary = Grayish,
    onBackground = Grayish,
    onSurface = Grayish,

)

@Composable
fun QuranicAudioAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(color = colors.primary)
}