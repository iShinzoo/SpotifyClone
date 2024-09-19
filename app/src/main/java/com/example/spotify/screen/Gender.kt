package com.example.spotify.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.add
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.spotify.ViewModel.MyViewModel
import com.example.spotify.dataclass.ButtonState
import com.example.spotify.ui.theme.dimens
import kotlin.text.clear


@Composable
fun MyButton(
    onButtonClicked: (Int) -> Unit,
    index: Int,
    buttonStates: MutableList<ButtonState>,
    viewModel: MyViewModel
) {
    val state = buttonStates[index]
    Button(
        onClick = {
            viewModel.gender = state.text
            viewModel.updateSelectedButtonIndex(index)
            onButtonClicked(index)
            onButtonClicked(index)
        },
        colors = ButtonDefaults.buttonColors(
            Color(0xFF121212),
        ),
        shape = RoundedCornerShape(28.dp),
        border  =
        if(MaterialTheme.dimens.isMedium || MaterialTheme.dimens.isExpanded)
            BorderStroke(1.5.dp, Color.Gray)
                    else
            BorderStroke(0.7.dp, Color.Gray),
                    modifier =  Modifier.
        wrapContentWidth().
        padding(end = 8.dp)
    ) {
        Text(
            text = state.text,
            color = if (state.isSelected) Color.White else Color.Gray,
            style = MaterialTheme.typography.titleSmall,
            fontWeight = MaterialTheme.typography.headlineMedium.fontWeight,
            maxLines = 1
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun Gender(navController: NavController) {
    val viewModel: MyViewModel = viewModel()
    val currentRoute by navController.currentBackStackEntryAsState()
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    if (currentRoute?.destination?.route != "signup") {
                        Text(
                            "Create account",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = MaterialTheme.typography.headlineMedium.fontWeight,
                            color = Color.White,
                        )
                    }
                },
                navigationIcon = {
                    if (currentRoute?.destination?.route != "signup") {
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
    ) {

        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .background(Color(0xFF121212))
                .verticalScroll(
                    rememberScrollState()
                )
        ) {
            Spacer(modifier = Modifier.height(36.dp))
            Text(
                "What's your gender?",
                color = Color.White,
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(8.dp)
            )


            val buttonStates = remember {
                    mutableStateListOf(
                        ButtonState(false, "Female", Color.Gray),
                        ButtonState(false, "Male", Color.Gray),
                        ButtonState(false, "Non-binary", Color.Gray),
                        ButtonState(false, "Other", Color.Gray),
                        ButtonState(false, "Prefer not to say ", Color.Gray),
                    )
            }

            buttonStates.forEach { button ->
                button.isSelected = button.text == viewModel.gender
            }

            FlowRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
            ) {
                buttonStates.forEachIndexed { index, button ->
                    MyButton(
                        onButtonClicked = {
                            viewModel.updateSelectedButtonIndex(index)
                            navController.navigate("namecreateacc")
                        },
                        index = index,
                        buttonStates = buttonStates,
                        viewModel = viewModel,
                    )
                }
            }
        }
    }
}


