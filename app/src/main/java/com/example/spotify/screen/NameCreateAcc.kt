package com.example.spotify.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.spotify.ViewModel.AuthState
import com.example.spotify.ViewModel.AuthViewModel
import com.example.spotify.ViewModel.MyViewModel
import com.example.spotify.dataclass.User
import dagger.hilt.android.AndroidEntryPoint


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NameCreateAcc(navController: NavController,authViewModel: AuthViewModel,viewModel: MyViewModel) {
    val authState = authViewModel.authState.observeAsState()
    val currentRoute by navController.currentBackStackEntryAsState()

    LaunchedEffect(authState.value) { // Use authState.value here
        Log.d("AuthState", "Auth state changed: ${authState.value}")
        if (authState.value is AuthState.Authenticated) {
            navController.navigate("langSelection"){
                popUpTo("nameCreateAcc"){
                    inclusive = true
                }
            }
        }else if (authState.value is AuthState.Error) {
            val errorMessage = (authState.value as AuthState.Error).message
            // Handle the error message, e.g., show a toast or snackbar
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    if(currentRoute?.destination?.route != "signup"){
                        Text("Create account",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = MaterialTheme.typography.headlineMedium.fontWeight,
                            color = Color.White,
                        )
                    }
                },
                navigationIcon = {
                    if (currentRoute?.destination?.route != "signup" ){
                        IconButton(onClick = {
                            navController.popBackStack()
                        }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back",
                                tint = Color.White,
                                modifier = Modifier.size(34.dp)
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    Color(0xFF121212)
                ),

                )
        }
    ){

        Column(modifier = Modifier.padding(it).fillMaxSize().background(Color(0xFF121212)).verticalScroll(rememberScrollState())) {
            Spacer(modifier = Modifier.height(20.dp))
            Text("What's your name?", color = Color.White, style = MaterialTheme.typography.headlineLarge, modifier = Modifier.padding(8.dp))
            TextField(
                value = viewModel.name,
                onValueChange = {
                    viewModel.name = it
                },
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                colors = TextFieldDefaults
                    .colors(
                        focusedContainerColor = Color(0xFF717171),
                        unfocusedContainerColor = Color(0xFF414141),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedTextColor = Color.White,
                        focusedTextColor = Color.White,
                        cursorColor = Color.White,
                        unfocusedIndicatorColor = Color.Transparent,
                    ),
            )

            Spacer(modifier = Modifier.padding(8.dp))

            Button(modifier = Modifier.align(Alignment.CenterHorizontally).padding(8.dp).size(221.dp, 60.dp),
                onClick = {
                        authViewModel.signup(viewModel)
                    Log.d("ClickSignup", "Email: ${viewModel.email}, Password: ${viewModel.password}") // Add this line
                    },

                colors = ButtonDefaults.buttonColors(
                    Color(0xFFFFFFFF),
                    contentColor = Color.Black,
                    disabledContainerColor = Color(0xFF5a5a5a),
                    disabledContentColor = Color(0xFF414141),
                ),
                enabled = if(viewModel.name.isNotEmpty()){
                    true
                } else {
                    false
                },
            ){
                Text(text = "Create account",
                    fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                    fontWeight = MaterialTheme.typography.headlineMedium.fontWeight,
                )
            }
        }
    }
}