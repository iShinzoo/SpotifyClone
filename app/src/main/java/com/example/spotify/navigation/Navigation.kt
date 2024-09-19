package com.example.spotify.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.spotify.ViewModel.AuthState
import com.example.spotify.ViewModel.AuthViewModel
import com.example.spotify.ViewModel.LangAndArtist
import com.example.spotify.ViewModel.MyViewModel
import com.example.spotify.ViewModel.SelectedArtists
import com.example.spotify.ViewModel.ShareViewModel
import com.example.spotify.screen.ClickSignup
import com.example.spotify.screen.DatePicker
import com.example.spotify.screen.EmailAndPass
import com.example.spotify.screen.Gender
import com.example.spotify.screen.NameCreateAcc
import com.example.spotify.screen.Password
import com.example.spotify.screen.SignUpPage
import com.example.spotify.screen.afterprofilecreation.ArtistSelection
import com.example.spotify.screen.afterprofilecreation.MusicSelection
import com.example.spotify.screen.afterprofilecreation.SearchScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation(navController: NavHostController, authViewModel: AuthViewModel,viewModel: MyViewModel) {
    val Langviewmodel:LangAndArtist = LangAndArtist()
    val UserSelectedArtists:SelectedArtists = SelectedArtists()
    NavHost(navController = navController, startDestination = "signup" ) {
        composable("signup") {
            val authState = authViewModel.authState.observeAsState()
            when (authState.value) {
                is AuthState.Authenticated -> MusicSelection(Langviewmodel,navController)
                is AuthState.Unauthenticated -> SignUpPage(navController)
                is AuthState.Error -> TODO()
                AuthState.Loading -> TODO()
                null -> TODO()
            }
        }
        composable("login") {
            EmailAndPass(navController)
        }
        composable("clicksignup"){
            ClickSignup(navController,viewModel)
        }

        composable("password"){
            Password(navController,viewModel)
        }

        composable("datepicker"){
            DatePicker(navController,viewModel)
        }

        composable("namecreateacc"){
            NameCreateAcc(navController,authViewModel,viewModel)
        }

        composable("gender"){
            Gender(navController)
        }

        composable("langSelection"){
            MusicSelection(Langviewmodel,navController)
        }

        composable("artistselection"){
            ArtistSelection(UserSelectedArtists,navController)
        }

        composable("searchandselectartists"){
            SearchScreen(navController, ShareViewModel())
        }


    }
}