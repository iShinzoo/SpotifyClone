package com.example.spotify.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.spotify.R
import com.example.spotify.ViewModel.MyViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Password(navController: NavController,viewModel: MyViewModel) {
    var isVisible by rememberSaveable { mutableStateOf(false) }
    val currentRoute by navController.currentBackStackEntryAsState()
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
            Spacer(modifier = Modifier.height(36.dp))
            Text("Create a password", color = Color.White, style = MaterialTheme.typography.headlineLarge, modifier = Modifier.padding(8.dp))
            TextField(
                value = viewModel.password,
                onValueChange = {
                    viewModel.password = it
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
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    val imageIcon = if(isVisible){
                        R.drawable.notvisible
                    } else {
                        R.drawable.visible
                    }

                    val description = if(isVisible){
                        "Hide password"
                    } else {
                        "Show password"
                    }

                    IconButton(
                        onClick = {
                            isVisible = !isVisible
                        }
                    ) {
                        Image(
                            painter = painterResource(id = imageIcon),
                            contentDescription = description,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                },
                visualTransformation = if(isVisible){
                    VisualTransformation.None
                } else
                    PasswordVisualTransformation()
            )

            Spacer(modifier = Modifier.padding(2.dp))

            Button(modifier = Modifier.align(Alignment.CenterHorizontally).padding(8.dp).size(121.dp, 60.dp),
                onClick = {
                    Log.d("ClickSignup", "Email: ${viewModel.email}, Password: ${viewModel.password}") // Add this line
                    navController.navigate("datepicker")
                },
                colors = ButtonDefaults.buttonColors(
                    Color(0xFFFFFFFF),
                    contentColor = Color.Black,
                    disabledContainerColor = Color(0xFF5a5a5a),
                    disabledContentColor = Color(0xFF414141),
                ),
                enabled = if(viewModel.password.length >= 8){
                    true
                } else {
                    false
                },
            ){
                Text(text = "Next",
                    fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                    fontWeight = MaterialTheme.typography.headlineMedium.fontWeight,
                )

            }
        }
    }
}