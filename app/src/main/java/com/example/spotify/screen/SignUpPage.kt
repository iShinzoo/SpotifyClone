package com.example.spotify.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.spotify.R
import com.example.spotify.ui.theme.dimens
import com.example.spotify.ui.theme.gradientBackground
import com.example.spotify.ui.theme.gradientColors
import androidx.compose.material3.Text
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.example.spotify.ui.theme.Inter


@OptIn(ExperimentalMaterial3WindowSizeClassApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SignUpPage(modifier: Modifier = Modifier) {
        Column(
        modifier = Modifier
            .fillMaxSize()
            .background(gradientBackground(isVertical = true, color = gradientColors)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(if(MaterialTheme.dimens.isCompanctMedian)
                            0.25f else 0.45f
                        ),
                    contentAlignment = Alignment.Center
                ){
                    Image(
                        painter = painterResource(id = R.drawable.spotify_home),
                        contentDescription = "Spotify Logo",
                        alignment = Alignment.Center,
                        modifier = Modifier
                            .padding(MaterialTheme.dimens.medium3)
                            .size(MaterialTheme.dimens.large)
                            .clip(CircleShape)

                    )
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .align(Alignment.CenterHorizontally),
                ){
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            text = "Millions of songs.\n \n Free on Spotify.",
                            fontSize = MaterialTheme.typography.displaySmall.fontSize,
                            modifier = Modifier
                                .padding(MaterialTheme.dimens.medium1),
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFFFFFFF)
                        )

                        Spacer(modifier = Modifier.height(MaterialTheme.dimens.medium2))

                        Button(
                            modifier = Modifier
                                .padding(5.dp)
                                .fillMaxWidth(0.9f)
                                .size(MaterialTheme.dimens.buttonHeight),
                            onClick = { /*TODO*/ },
                            colors = ButtonDefaults.buttonColors(
                                Color(0xFF1DB954),
                            ),

                        ) {
                            Text(
                                text = "Sign up free",
                                color = Color(0xFF000000),
                                fontWeight = FontWeight.Bold,

                            )
                        }

                        Button(
                            modifier = Modifier
                                .padding(5.dp)
                                .fillMaxWidth(0.9f)
                                .size(MaterialTheme.dimens.buttonHeight),
                            onClick = { /*TODO*/ },
                            colors = ButtonDefaults.buttonColors(
                                Color(0xFF000000),
                            ),
                            border = BorderStroke(1.dp, Color(0xFF3e3e3e))

                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Start,
                                modifier = Modifier.fillMaxSize(),

                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.phonenew),
                                    contentDescription = "Phone Icon",
                                    tint = Color(0xFFFFFFFF),
                                    modifier = Modifier.size(24.dp),
                                )

                                Text(
                                    text = "Continue with phone number",
                                    color = Color(0xFFFFFFFF),
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                                    textAlign = TextAlign.Center,
                                    modifier= Modifier.weight(1f),
                                )
                        }
                        }

                        Button(
                            modifier = Modifier
                                .padding(5.dp)
                                .fillMaxWidth(0.9f)
                                .size(MaterialTheme.dimens.buttonHeight),
                            onClick = { /*TODO*/ },
                            colors = ButtonDefaults.buttonColors(
                                Color(0xFF000000),
                            ),
                            border = BorderStroke(1.dp, Color(0xFF3e3e3e))
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Start,
                                modifier = Modifier.fillMaxSize(),

                                ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.googlenew),
                                    contentDescription = "Phone Icon",
                                    tint = Color.Unspecified,
                                    modifier = Modifier.size(24.dp),


                                )

                                Text(
                                    text = "Continue with Google",
                                    color = Color(0xFFFFFFFF),
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                                    textAlign = TextAlign.Center,
                                    modifier= Modifier.weight(1f)
                                )
                            }
                        }

                        Button(
                            modifier = Modifier
                                .padding(5.dp)
                                .fillMaxWidth(0.9f)
                                .size(MaterialTheme.dimens.buttonHeight),
                            onClick = { /*TODO*/ },
                            colors = ButtonDefaults.buttonColors(
                                Color(0xFF000000),
                            ),
                            border = BorderStroke(1.dp, Color(0xFF3e3e3e))

                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Start,
                                modifier = Modifier.fillMaxSize(),

                                ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.facebook),
                                    contentDescription = "Phone Icon",
                                    tint = Color.Unspecified,
                                    modifier = Modifier.size(24.dp),

                                    )
                                Text(
                                    text = "Continue with Facebook",
                                    color = Color(0xFFFFFFFF),
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                                    textAlign = TextAlign.Center,
                                    modifier= Modifier.weight(1f),
                                )
                            }
                        }

                        Button(
                            modifier = Modifier
                                .padding(5.dp)
                                .fillMaxWidth(0.9f)
                                .size(MaterialTheme.dimens.buttonHeight),
                            onClick = { /*TODO*/ },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Transparent, // Transparent background
                                contentColor = Color(0xFF0d0d0d) // Text color
                            ),
                            border = BorderStroke(0.dp, Color.Transparent),
                            elevation = ButtonDefaults.buttonElevation(0.dp),


                        ){
                            Text(
                                text = "Log in",
                                color = Color(0xFFFFFFFF),
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                                textAlign = TextAlign.Center,
                                modifier= Modifier.weight(1f),
                            )
                        }

                    }
                }
    }
}