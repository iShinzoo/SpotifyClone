package com.example.spotify.ViewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.spotify.dataclass.ArtistSelected

class SelectedArtists:ViewModel() {
    private val _selectedArtists = mutableStateOf<List<ArtistSelected>>(emptyList())
    val selectedArtists: State<List<ArtistSelected>> = _selectedArtists

    fun updateSelectedArtists(artist: ArtistSelected) {
        if (artist in _selectedArtists.value) {
            _selectedArtists.value -= artist
        } else {
            _selectedArtists.value += artist
        }
    }
}