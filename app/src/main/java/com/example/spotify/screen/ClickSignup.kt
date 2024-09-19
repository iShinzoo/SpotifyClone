package com.example.spotify.screen

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.spotify.ViewModel.AuthViewModel
import com.example.spotify.ViewModel.MyViewModel
import com.example.spotify.ui.theme.dimens
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClickSignup(navController: NavController,viewModel: MyViewModel) {
    val auth: FirebaseAuth = FirebaseAuth.getInstance()
    val currentRoute by navController.currentBackStackEntryAsState()
    var showDialog by rememberSaveable { mutableStateOf(false) }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    if(currentRoute?.destination?.route != "signup"){
                        Text(
                            "Create account",
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
        Column(modifier = Modifier
            .padding(it)
            .fillMaxSize()
            .background(Color(0xFF121212))
            .verticalScroll(rememberScrollState())) {
            Spacer(modifier = Modifier.height(36.dp))
            Text("What's your email?", color = Color.White, style = MaterialTheme.typography.headlineLarge, modifier = Modifier.padding(8.dp))
            TextField(
                value = viewModel.email,
                onValueChange = { email->
                    viewModel.email = email
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
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

            Spacer(modifier = Modifier.padding(2.dp))

            Button(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(8.dp)
                    .size(121.dp, 60.dp),
                onClick = {
                    val tempPassword = generateTempPassword()
                    auth.createUserWithEmailAndPassword(viewModel.email, tempPassword)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                //Email doesn't exist, delete the temporary user
                                task.result?.user?.delete()
                                navController.navigate("password")
                            } else {
                                if (task.exception is
                                            FirebaseAuthUserCollisionException
                                ) {
                                    showDialog = true
                                }
                            }
                        }

                },
                colors = ButtonDefaults.buttonColors(
                    Color(0xFFFFFFFF),
                    contentColor = Color.Black,
                    disabledContainerColor = Color(0xFF5a5a5a),
                    disabledContentColor = Color(0xFF414141),
                ),
                enabled = if (viewModel.email.isNotEmpty() && viewModel.email.contains("@gmail.com")) {
                    true
                } else {
                    false
                },
            ){
                Text(
                    text = "Next",
                    fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                    fontWeight = MaterialTheme.typography.headlineMedium.fontWeight,
                )

            }
            if(showDialog){
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    title = { Text("This email is already connected to an account",
                        textAlign = TextAlign.Center, fontWeight = FontWeight.Bold) },
                    text = {
                        Column( modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                        Text(
                            "Do you want to log in instead?",
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Medium
                        )
                    } },
                    confirmButton = {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Button(
                                onClick = {
                                    navController.navigate("login")
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xff1ed760),
                                    contentColor = Color.White
                                ),
                                modifier = Modifier
                                    .wrapContentWidth() // Button will now take its own width
                                    .padding(8.dp)
                            ) {
                                Text("Go to login", textAlign = TextAlign.Center, color = Color.Black)
                            }
                        }
                    },
                    dismissButton ={
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 8.dp),
                            contentAlignment = Alignment.Center
                        ){
                        Button(
                            onClick = {
                                showDialog = false
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Transparent,
                                contentColor = Color.DarkGray
                            ),
                            modifier = Modifier.wrapContentWidth()
                        ) {
                            Text("Close", textAlign = TextAlign.Center)
                        }
                            }
                    },
                    containerColor = Color.White,
                    shape = MaterialTheme.shapes.medium,
                    modifier = Modifier

                )
            }

        }
    }
}

fun generateTempPassword(): String {
    val random = java.util.Random()
    val characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
    val passwordLength = 8
    val password = StringBuilder()
    for (i in 0 until passwordLength) {
        val randomIndex = random.nextInt(characters.length)
        password.append(characters[randomIndex])
    }
    return password.toString()

}
