package com.example.spotify

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.spotify.ViewModel.AuthViewModel
import com.example.spotify.ViewModel.MyViewModel
import com.example.spotify.api.SpotifyAPI
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
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var spotifyAPI: SpotifyAPI

    @RequiresApi(Build.VERSION_CODES.O)
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class, DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            navigationBarStyle = SystemBarStyle.auto(
                Color.Transparent.toArgb(),Color.Transparent.toArgb()
            )
        )
        val splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition{true}


        //Adding delay to splash screen
        CoroutineScope(Dispatchers.Main).launch {
            delay(260)
            splashScreen.setKeepOnScreenCondition{false}
        }
        setContent {
            SpotifyTheme {
                val authViewModel = AuthViewModel()
                val viewModel : MyViewModel =   hiltViewModel()
                val navController = rememberNavController()
                val authState = authViewModel.authState.value
                Navigation(navController,authViewModel,viewModel)
                }
            }
        }
    }
