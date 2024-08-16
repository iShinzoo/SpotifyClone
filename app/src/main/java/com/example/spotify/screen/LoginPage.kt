package com.example.spotify.screen

import android.text.method.PasswordTransformationMethod
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.spotify.R
import com.example.spotify.ui.theme.dimens


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailAndPass(
    navController: NavHostController
) {
    // Email and password fields
    var emailInput by rememberSaveable { mutableStateOf("") }
    var pass by rememberSaveable { mutableStateOf("") }
    val currentRoute by navController.currentBackStackEntryAsState()
    var isVisible by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    // Title
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
        if(MaterialTheme.dimens.isMedium){
            Column(modifier = Modifier.padding(it).
            fillMaxSize().
            verticalScroll(rememberScrollState()).
            background(Color(0xFF121212)),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Email and password fields
                    Text(text = "Email or username",
                        color = Color.White,
                        fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                        fontWeight = MaterialTheme.typography.headlineLarge.fontWeight,
                        modifier = Modifier.padding(8.dp).align(Alignment.Start)
                    )
                    TextField(
                        value = emailInput,
                        onValueChange = {
                            emailInput = it
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

                    Spacer(modifier = Modifier.size(MaterialTheme.dimens.medium3))
                    Text(text = "Password",
                        color = Color.White,
                        fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                        fontWeight = MaterialTheme.typography.headlineLarge.fontWeight,
                        modifier = Modifier.padding(8.dp).align(Alignment.Start)
                    )
                    TextField(
                        value = pass,
                        onValueChange = {
                            pass = it
                        },
                        modifier = Modifier.fillMaxWidth().padding(8.dp),
                        colors = TextFieldDefaults
                            .colors(focusedContainerColor = Color(0xFF717171),
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

                    Spacer(modifier = Modifier.size(MaterialTheme.dimens.medium3))

                    Button(modifier = Modifier.padding(8.dp).size(150.dp, 70.dp),
                        onClick = {
                            // Log in
                        },
                        colors = ButtonDefaults.buttonColors(
                            Color(0xFFFFFFFF),
                            contentColor = Color.Black,
                            disabledContainerColor = Color(0xFF5a5a5a),
                            disabledContentColor = Color(0xFF414141),
                        ),
                        enabled = if(emailInput.isNotEmpty() && pass.isNotEmpty()){
                            true
                        } else {
                            false
                        },
                    ){
                        Text(text = "Log in",
                            fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                            fontWeight = MaterialTheme.typography.headlineLarge.fontWeight,
                        )

                    }
                }
        }
        else{
            Column(modifier = Modifier.padding(it).fillMaxSize().verticalScroll(rememberScrollState()).
            background(Color(0xFF121212))) {
                // Email and password fields
                Text(text = "Email or username",
                    color = Color.White,
                    fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                    fontWeight = MaterialTheme.typography.headlineLarge.fontWeight,
                    modifier = Modifier.padding(8.dp)
                )
                TextField(
                    value = emailInput,
                    onValueChange = {
                        emailInput = it
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

                Spacer(modifier = Modifier.size(MaterialTheme.dimens.medium3))
                Text(text = "Password",
                    color = Color.White,
                    fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                    fontWeight = MaterialTheme.typography.headlineLarge.fontWeight,
                    modifier = Modifier.padding(8.dp)
                )
                TextField(
                    value = pass,
                    onValueChange = {
                        pass = it
                    },
                    modifier = Modifier.fillMaxWidth().padding(8.dp),
                    colors = TextFieldDefaults
                        .colors(focusedContainerColor = Color(0xFF717171),
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

                Spacer(modifier = Modifier.size(MaterialTheme.dimens.medium3))

                Button(modifier = Modifier.align(Alignment.CenterHorizontally).padding(8.dp).size(145.dp, 60.dp),
                    onClick = {
                        // Log in
                    },
                    colors = ButtonDefaults.buttonColors(
                        Color(0xFFFFFFFF),
                        contentColor = Color.Black,
                        disabledContainerColor = Color(0xFF5a5a5a),
                        disabledContentColor = Color(0xFF414141),
                    ),
                    enabled = if(emailInput.isNotEmpty() && pass.isNotEmpty()){
                        true
                    } else {
                        false
                    },
                ){
                    Text(text = "Log in",
                        fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                        fontWeight = MaterialTheme.typography.headlineLarge.fontWeight,
                    )

                }
            }
        }
    }
}