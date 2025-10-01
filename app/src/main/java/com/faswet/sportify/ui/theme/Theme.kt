package com.faswet.sportify.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Mirage,
    onPrimary = White,
    background = Mirage,
    surface = Mirage,
    onSurface = White,
    error = Red,
    onError = White,
    errorContainer = Red,
    onErrorContainer = White,
    outline = DarkGray,
    onBackground = White,
    onSecondary = White,
    secondary = OxfordBlue,
    surfaceVariant = GullGray,
)

private val LightColorScheme = lightColorScheme(
    primary = White,
    onPrimary = Mirage,
    background = White,
    surface = White,
    onSurface = Mirage,
    error = Red,
    onError = White,
    errorContainer = Red,
    onErrorContainer = White,
    outline = LightGray,
    onBackground = Black,
    onSecondary = Black,
    secondary = CatskillWhite,
    surfaceVariant = OxfordBlue,
)

@Composable
fun SportifyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current

    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val typography = provideTypography(if (darkTheme) White else Black)

    MaterialTheme(
        colorScheme = colorScheme,
        typography = typography,
        content = content
    )
}

val MaterialTheme.dimens: Dimens
    get() = Dimens()