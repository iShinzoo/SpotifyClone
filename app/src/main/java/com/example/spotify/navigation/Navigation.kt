package com.example.spotify.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.spotify.screen.ClickSignup
import com.example.spotify.screen.DatePicker
import com.example.spotify.screen.EmailAndPass
import com.example.spotify.screen.Gender
import com.example.spotify.screen.NameCreateAcc
import com.example.spotify.screen.Password
import com.example.spotify.screen.SignUpPage

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "signup" ) {
        composable("signup") {
             SignUpPage(navController)
        }
        composable("login") {
            EmailAndPass(navController)
        }
        composable("clicksignup"){
            ClickSignup(navController)
        }

        composable("password"){
            Password(navController)
        }

        composable("datepicker"){
            DatePicker(navController)
        }

        composable("namecreateacc"){
            NameCreateAcc(navController)
        }

        composable("gender"){
            Gender(navController)
        }

    }
}