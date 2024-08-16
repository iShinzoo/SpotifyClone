package com.example.spotify

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.spotify.diffscreensize.CompactDimens
import com.example.spotify.diffscreensize.CompactMediumDimens
import com.example.spotify.diffscreensize.CompactSmallDimens
import com.example.spotify.diffscreensize.ExpandedDimens
import com.example.spotify.diffscreensize.MediumDimens
import com.example.spotify.navigation.Navigation
import com.example.spotify.screen.SignUpPage
import com.example.spotify.ui.theme.AppUtils
import com.example.spotify.ui.theme.CompactMediumTypography
import com.example.spotify.ui.theme.CompactTypography
import com.example.spotify.ui.theme.ExpandedTypography
import com.example.spotify.ui.theme.MediumTypography
import com.example.spotify.ui.theme.SpotifyTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition{true}

        //Adding delay to splash screen
        CoroutineScope(Dispatchers.Main).launch {
            delay(260)
            splashScreen.setKeepOnScreenCondition{false}
        }
        setContent {
            SpotifyTheme {
                val navController = rememberNavController()
                Navigation(navController)
                }
            }
        }
    }

