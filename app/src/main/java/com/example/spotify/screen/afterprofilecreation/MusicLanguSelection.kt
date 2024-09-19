package com.example.spotify.screen.afterprofilecreation

import android.util.Log
import androidx.collection.emptyLongSet
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.then
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.spotify.R
import com.example.spotify.ViewModel.LangAndArtist
import com.example.spotify.dataclass.Genre
import com.example.spotify.ui.theme.blue
import com.example.spotify.ui.theme.brown
import com.example.spotify.ui.theme.dimens
import com.example.spotify.ui.theme.gradientBackground
import com.example.spotify.ui.theme.gradientColors
import com.example.spotify.ui.theme.green
import com.example.spotify.ui.theme.imageGrad1
import com.example.spotify.ui.theme.pink

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MusicSelection(viewModel: LangAndArtist = viewModel(),navController: NavController) {
    val genres = mapOf(
        Genre("Hindi", R.drawable.hindipng,Color(0xFFd43929)) to R.drawable.hindipng,
        Genre("International", R.drawable.international,Color(0xFFce821d)) to R.drawable.international,
        Genre("Punjabi", R.drawable.punjabi,Color(0xFF8f217c)) to R.drawable.punjabi,
        Genre("Tamil", R.drawable.tamil,Color(0xFFd5a653)) to R.drawable.tamil,
        Genre("Telugu", R.drawable.telgu,gradientBackground(isVertical = false, color = green)) to R.drawable.telgu,
        Genre("Malayalam", R.drawable.malyalum,gradientBackground(isVertical = false, color = imageGrad1)) to R.drawable.malyalum,
        Genre("Marathi", R.drawable.marathi,gradientBackground(isVertical = false, color = brown)) to R.drawable.marathi,
        Genre("Gujarati", R.drawable.gujrati,gradientBackground(isVertical = false, color = pink)) to R.drawable.gujrati,
        Genre("Bengali", R.drawable.bengali,gradientBackground(isVertical = false, color = blue)) to R.drawable.bengali,
        Genre("Kannada", R.drawable.kannada,Color(0xFFbb1726)) to R.drawable.kannada,
        // Add more genres and images as needed

    )

    var selectedGenres by rememberSaveable { mutableStateOf<List<Genre>>(emptyList()) }
    LaunchedEffect(viewModel) {
        snapshotFlow { viewModel.selectedGenres.value }
            .collect { newSelectedGenres ->
                selectedGenres = newSelectedGenres
            }
    }

    Log.d("selectedGenres", selectedGenres.isEmpty().toString())
    Log.d("selectedGenres", viewModel.selectedGenres.value.isEmpty().toString())

     Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text("What music do you like?",
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = MaterialTheme.typography.headlineMedium.fontWeight,
                            color = Color.White,
                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        Color(0xFF121212)
                    ),
                    )
            },
         bottomBar = {
             AnimatedVisibility(
                 visible = selectedGenres.isNotEmpty(),
                 enter = slideInVertically { fullHeight -> fullHeight },
                 exit = slideOutVertically { fullHeight -> -fullHeight }
             ) {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(8.dp),
                        horizontalArrangement = Arrangement.Center,
                    ){
                        Button(
                            onClick = {
                                navController.navigate("artistselection")
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
        ){
         innerPadding ->
            FlowRow(
                modifier = Modifier
                    .background(Color(0xFF121212))
                    .padding(innerPadding)
                    .padding(start = 8.dp)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                verticalArrangement = Arrangement.spacedBy(26.dp)

            ) {
                genres.forEach { (genre, image,) ->
                    val isSelected = viewModel.selectedGenres.value.contains(genre)
                    GenreCard(
                        genre,
                        image,
                        onGenreSelected = { genre,_ ->
                            viewModel.updateSelectedGenres(genre)
                            Log.d("selectedGenres", viewModel.selectedGenres.value.toString())
                        },
                        isSelected = isSelected
                        )
                }

            }
        }
}

@Composable
fun GenreCard(genre: Genre, image: Int,onGenreSelected:(Genre, Boolean) -> Unit, isSelected: Boolean) {
    // Add your code here
        val buttonColor = when(genre.background){
        is Color -> genre.background
        is Brush -> Color.Transparent
        else -> Color.Transparent
    }
    Button(
        onClick = {
            onGenreSelected(genre, !isSelected)
        },
        modifier = if(MaterialTheme.dimens.isExpanded)
            Modifier
            .size(395.dp, 200.dp)
        else if(MaterialTheme.dimens.isMedium)
            Modifier
            .size(240.dp, 120.dp)
        else {
            Modifier
                .size(180.dp, 100.dp)
    },
        colors = ButtonDefaults.buttonColors(
            buttonColor
        ),
        shape = RoundedCornerShape(10.dp),
        contentPadding = PaddingValues(0.dp)


    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .then(
                    when (genre.background) {
                        is Color -> Modifier.background(genre.background)
                        is Brush -> Modifier.background(genre.background)
                        else -> Modifier
                    }
                )
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = genre.name,
                modifier = Modifier
                    .size(395.dp, 200.dp)
                    .align(Alignment.BottomEnd)

            )
            Text(
                genre.name,
                color = Color.White,
                modifier = Modifier.padding(8.dp),
                fontWeight = FontWeight.Bold
            )

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
}
