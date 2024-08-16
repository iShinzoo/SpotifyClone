package com.example.spotify.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import com.example.spotify.MainActivity
import com.example.spotify.diffscreensize.CompactDimens
import com.example.spotify.diffscreensize.CompactMediumDimens
import com.example.spotify.diffscreensize.CompactSmallDimens
import com.example.spotify.diffscreensize.ExpandedDimens
import com.example.spotify.diffscreensize.MediumDimens

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun SpotifyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    activity: Activity = LocalContext.current as MainActivity,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val windowSizeClass = calculateWindowSizeClass(activity = activity)
    println("9911_windowSizeClass: $windowSizeClass")
    val config = LocalConfiguration.current

    var typography = CompactTypography
    var appDimens = CompactDimens

    when(windowSizeClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            when (windowSizeClass.heightSizeClass) {
                WindowHeightSizeClass.Compact -> {
                    if (config.screenWidthDp < 690) {
                        println("9911_CompactSmallDimens")
                        appDimens = CompactSmallDimens
                        typography = CompactTypography
                    } else if (config.screenWidthDp < 599) {
                        println("9911_CompactMediumDimens")
                        appDimens = CompactMediumDimens
                        typography = CompactMediumTypography
                    } else {
                        println("9911_CompactDimens")
                        appDimens = CompactDimens
                        typography = CompactTypography
                    }
                }

                WindowHeightSizeClass.Medium -> {
                    println("9911_CompactMediumDimens")
                    appDimens = CompactMediumDimens
                    typography = CompactMediumTypography
                }

                else -> {
                    println("9911_CompactDimens")
                    appDimens = CompactDimens
                    typography = CompactTypography
                }
            }
        }

        WindowWidthSizeClass.Medium -> {
            println("9911_MediumDimens")
            appDimens = MediumDimens
            typography = MediumTypography
        }

        else -> {
            when (windowSizeClass.heightSizeClass) {
                WindowHeightSizeClass.Compact -> {
                    println("9911_CompactDimens")
                    appDimens = CompactDimens
                    typography = CompactTypography
                }

                WindowHeightSizeClass.Medium -> {
                    println("9911_CompactMediumDimens")
                    appDimens = ExpandedDimens
                    typography = CompactMediumTypography
                }

                else -> {
                    println("9911_ExpandedDimens")
                    appDimens = ExpandedDimens
                    typography = ExpandedTypography
                }
            }
        }
    }

    AppUtils(appDimens = appDimens) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = typography,
            content = content
        )
    }
}

val MaterialTheme.dimens
@Composable
get() = LocalAppDimens.current