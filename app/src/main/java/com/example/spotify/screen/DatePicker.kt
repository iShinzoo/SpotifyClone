package com.example.spotify.screen

import android.health.connect.datatypes.WheelchairPushesRecord
import android.os.Build
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.commandiron.wheel_picker_compose.WheelDatePicker
import com.commandiron.wheel_picker_compose.WheelDateTimePicker
import com.commandiron.wheel_picker_compose.core.TimeFormat
import com.commandiron.wheel_picker_compose.core.WheelPickerDefaults
import com.example.spotify.ViewModel.MyViewModel
import com.example.spotify.ui.theme.dimens
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePicker(navController: NavController,viewModel: MyViewModel) {
    MaterialTheme {
        var showDatePicker by rememberSaveable { mutableStateOf(true) }
        var selectedDob by rememberSaveable { mutableStateOf<LocalDate?>(null) }
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
            ) { innerPadding ->
            BackHandler {
                navController.popBackStack()
            }
            Column(
                modifier = Modifier.
                    padding(innerPadding)
                    .background(Color(0xFF121212))
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "What's your date of birth?",
                    color = Color.White,
                    style = MaterialTheme.typography.titleLarge,
                    fontSize = if(MaterialTheme.dimens.isCompactHeight)
                        MaterialTheme.typography.headlineSmall.fontSize
                                else
                        MaterialTheme.typography.headlineLarge.fontSize,
                    fontWeight = MaterialTheme.typography.headlineMedium.fontWeight,
                )
                Spacer(modifier = Modifier.height(20.dp))
                val CurrentDate = LocalDate.now()
                val year = CurrentDate.year
                val month = CurrentDate.monthValue
                val day = CurrentDate.dayOfMonth

                WheelDatePicker(
                    startDate = LocalDate.of(2003, 9, 21),
                    minDate = LocalDate.of(1900, 1, 1),
                    maxDate = LocalDate.of(year, month, day),
                    size = if(MaterialTheme.dimens.isCompactHeight)
                        DpSize(380.dp, 90.dp)
                    else
                        DpSize(390.dp, 300.dp),
                    rowCount = 3,
                    textStyle = MaterialTheme.typography.titleLarge,
                    textColor = Color.White,
                    selectorProperties = WheelPickerDefaults.selectorProperties(
                        enabled = true,
                        shape = RoundedCornerShape(16.dp),
                        color = Color(0xFFf1faee).copy(alpha = 0.2f),
                        border = BorderStroke(2.dp, Color(0xFFf1faee))
                    )
                ){ date ->
                    selectedDob = date


                }

                Spacer(modifier = Modifier.height(20.dp))

                Button(modifier = Modifier.align(Alignment.CenterHorizontally).padding(8.dp).then(
                if(MaterialTheme.dimens.isCompactHeight)
                Modifier.size(121.dp, 60.dp)
                        else
                    Modifier.size(141.dp, 70.dp),),
                    onClick = {
                        navController.navigate("gender")
                    },
                    colors = ButtonDefaults.buttonColors(
                        Color(0xFFFFFFFF),
                        contentColor = Color.Black,
                        disabledContainerColor = Color(0xFF5a5a5a),
                        disabledContentColor = Color(0xFF414141),
                    ),
                ){
                    Text(text = "Next",
                        fontSize = if(MaterialTheme.dimens.isCompactHeight)
                            MaterialTheme.typography.headlineSmall.fontSize
                                    else
                            MaterialTheme.typography.headlineMedium.fontSize,
                        fontWeight = MaterialTheme.typography.headlineMedium.fontWeight,
                    )

                }
            }
        }
    }
}