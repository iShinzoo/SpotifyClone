package com.example.spotify.screen.afterprofilecreation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.spotify.ViewModel.ArtistDataVIewModel
import com.example.spotify.ViewModel.MyViewModel
import com.example.spotify.ViewModel.SelectedArtists
import com.example.spotify.ViewModel.ShareViewModel
import com.example.spotify.dataclass.ArtistSelected
import com.example.spotify.dataclass.Artists.Image

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun ArtistSelection(viewModel: SelectedArtists = viewModel(), navController: NavController) {
    val artistViewModel: ArtistDataVIewModel = hiltViewModel()

    var searchQuery by rememberSaveable { mutableStateOf("") }
    var isFocused by remember { mutableStateOf(false) }
    val listofartists = listOf(
        ArtistSelected("Arijit Singh"),
        ArtistSelected("Armaan Malik"),
        ArtistSelected("Atif Aslam"),
        ArtistSelected("Badshah"),
        ArtistSelected("Sonu Nigam"),
        ArtistSelected("Darshan Raval"),
        ArtistSelected("Diljit Dosanjh"),
        ArtistSelected("Guru Randhawa"),
        ArtistSelected("Honey Singh"),
        ArtistSelected("Ed Sheeran"),
        ArtistSelected("Justin Bieber"),
        ArtistSelected("Shawn Mendes"),
        ArtistSelected("Taylor Swift"),
        ArtistSelected("The Weeknd"),
        ArtistSelected("Ariana Grande"),
        ArtistSelected("Billie Eilish"),
        ArtistSelected("Dua Lipa"),
        ArtistSelected("Katy Perry"),
        ArtistSelected("Lady Gaga"),
        ArtistSelected("Selena Gomez"),
        ArtistSelected("Shakira"),
    )

    var selectedArtist by rememberSaveable { mutableStateOf<List<ArtistSelected>>(emptyList()) }
    LaunchedEffect(viewModel) {
        snapshotFlow { viewModel.selectedArtists.value }
            .collect { newSelectedArtists ->
                selectedArtist = newSelectedArtists
            }
    }

    val artistImages by artistViewModel.artistImage.collectAsState()


    val listoflanguages = listOf(
        Pair(0, "For You"), Pair(1, "Hindi"), Pair(2, "Punjabi"), Pair(3, "International")
    )


    LaunchedEffect(searchQuery) {
        listofartists.forEach { artist ->
            artistViewModel.fetchArtists(artist.name)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    Color(0xFF121212)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxSize(0.25f),

                navigationIcon = {
                    Column {
                        Text(
                            "Choose 3 or more artists you like.",
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = MaterialTheme.typography.headlineMedium.fontWeight,
                            fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                            color = Color.White,
                            modifier = Modifier.padding(8.dp)
                        )
                        TextField(
                            value = searchQuery,
                            onValueChange = {
                                searchQuery = it
                            },
                            label = {
                                Text(
                                    "Search",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = Color.Gray,
                                )
                            },
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color(0xFFffffff),
                                unfocusedContainerColor = Color(0xFFffffff),
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                cursorColor = Color.Green,

                                ),
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth()
                                .onFocusChanged { focusState ->
                                    isFocused = focusState.isFocused
                                    if (isFocused) {
                                        navController.navigate("searchandselectartists")
                                    }
                                },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = "Search",
                                    tint = Color.Black,
                                    modifier = Modifier.size(44.dp)
                                )
                            },
                            shape = TextFieldDefaults.shape,
                        )
                        FlowRow {
                            val viewModel: MyViewModel = viewModel()
                            listoflanguages.forEachIndexed() { index, langIndex ->
                                Buttonsforlang(
                                    Lindex = langIndex.first,
                                    string = langIndex.second,
                                    isSelected = viewModel.selectedlangIndx == index,
                                    onLangClick = {
                                        viewModel.selectedlangIndx = index
                                    }
                                )
                            }
                        }
                    }
                }
            )
        },

        bottomBar = {
                AnimatedVisibility(
                    visible = selectedArtist.isNotEmpty(),
                    enter = slideInVertically { fullHeight -> fullHeight },
                    exit = slideOutVertically { fullHeight -> -fullHeight },
                    modifier = Modifier.background(Color.Transparent)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.Center,
                    ){
                        Button(
                            onClick = {
                            },
                            modifier = Modifier,
                            colors = ButtonDefaults.buttonColors(
                                Color(0xFFFFFFFF)
                            ),
                        ){
                            Text("Next", color = Color.Black)
                        }
                    }
                }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .background(Color(0xFF121212))
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            FlowRow(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                for(artist in listofartists){
                    val isSelected = selectedArtist.contains(artist)
                    ArtistsCard(
                        artist = artist,
                        isSelected = isSelected,
                        onArtistSelected = { artist,_ ->
                            viewModel.updateSelectedArtists(artist)
                        },
                        artistImages = artistImages
                    )
                }

            }

        }


    }
}


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun SearchScreen(navController: NavController, viewModel: ShareViewModel) {
        val focusRequest = remember { FocusRequester() }
        var searchQuery by rememberSaveable { mutableStateOf("") }

        LaunchedEffect(Unit) {
            focusRequest.requestFocus()
        }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        TextField(
                            value = searchQuery,
                            onValueChange = {
                                searchQuery = it
                            },
                            placeholder =
                            if (searchQuery.isEmpty()) {
                                {
                                    Text(
                                        "Search",
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = Color.Gray,
                                    )
                                }
                            } else {
                                {
                                }
                            },
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color(0xFF2b2a2b),
                                unfocusedContainerColor = Color(0xFF2b2a2b),
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                focusedTextColor = Color.White,
                                unfocusedTextColor = Color.White,
                                cursorColor = Color.Green,

                                ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .focusRequester(focusRequest),
                            shape = TextFieldDefaults.shape,
                            leadingIcon = {
                                IconButton(
                                    onClick = {
                                        navController.popBackStack()
                                    }) {
                                    Icon(
                                        Icons.Filled.ArrowBack,
                                        contentDescription = "Back",
                                        tint = Color.White,
                                        modifier = Modifier.size(29.dp)
                                    )
                                }
                            },
                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        Color(0xFF121212)
                    ),

                    )
            }
        ) { innerPadding ->
            val artists by viewModel.artistResults.collectAsState()
            LazyColumn(
                modifier = Modifier
                    .padding(innerPadding)
                    .background(Color(0xFF121212))
                    .fillMaxSize()
            ) {


            }
        }
    }

@Composable
fun Buttonsforlang(
    Lindex: Int,
    string: String,
    isSelected: Boolean = false,
    onLangClick: () -> Unit
) {
    Button(
        onClick = {
            onLangClick()
        },
        modifier = Modifier
            .wrapContentWidth()
            .padding(end = 5.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor =
            if (isSelected)
                Color(0xFF14833d)
            else
                Color(0xFF121212)
        ),
        border = BorderStroke(2.dp, Color.Gray)
    ) {
        Text(string, color = Color.White)
    }
}


@Composable
fun ArtistsCard(artist: ArtistSelected, isSelected: Boolean, onArtistSelected: (ArtistSelected, Boolean) -> Unit, artistImages: Map<String, List<String>>) {
    Box(
        modifier = Modifier
            .size(130.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFF121212))
    ){
        Card(
            modifier = Modifier
                .padding(8.dp)
                .size(120.dp)
                .clickable { }
                .clip(CircleShape),

            onClick = {
                onArtistSelected(artist, !isSelected)
            }
        ) {
            val imageUrls = artistImages[artist.name]
            imageUrls?.firstOrNull()?.let{ imageUrl ->
                AsyncImage(
                    model = imageUrl,
                    contentDescription = "Artist Image",
                    modifier = Modifier.fillMaxSize()
                )

            }
        }

        if(isSelected){
            Icon(
                imageVector = Icons.Filled.CheckCircle,
                contentDescription = "selected",
                tint = Color.White,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(8.dp)
            )
        }


    }
}