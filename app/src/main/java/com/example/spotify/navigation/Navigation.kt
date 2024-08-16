package com.example.spotify.navigation

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.spotify.screen.EmailAndPass
import com.example.spotify.screen.SignUpPage

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "signup" ) {
        composable("signup") {
             SignUpPage(navController)
        }
        composable("login") {
            EmailAndPass(navController)
        }

    }
}