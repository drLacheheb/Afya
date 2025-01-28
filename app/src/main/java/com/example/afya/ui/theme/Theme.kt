package com.example.afya.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = SapphireBlue,
    secondary = CadetBlue,
    tertiary = LavenderGray,
    background = MedicalWhite,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = SlateGray,
    onSurface = SlateGray,
    error = CoralRed,
    onError = Color.White
)

private val DarkColorScheme = darkColorScheme(
    primary = NavyBlue,
    secondary = SteelTeal,
    tertiary = TwilightPurple,
    background = Color(0xFF121212),
    surface = Color(0xFF1E1E1E),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color(0xFFE0E0E0),
    onSurface = Color(0xFFE0E0E0),
    error = CoralRed,
    onError = Color.Black
)

@Composable
fun AfyaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {

    // Choose color scheme based on theme mode
    val colors = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}