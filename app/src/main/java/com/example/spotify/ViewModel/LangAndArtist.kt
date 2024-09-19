package com.example.spotify.ViewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.spotify.dataclass.Genre

class LangAndArtist:ViewModel() {
    private val _selectedGenres = mutableStateOf<List<Genre>>(emptyList())
    val selectedGenres: State<List<Genre>> = _selectedGenres

    fun updateSelectedGenres(genre: Genre) {
        if (genre in _selectedGenres.value) {
            _selectedGenres.value -= genre
        } else {
            _selectedGenres.value += genre
        }

    }


}