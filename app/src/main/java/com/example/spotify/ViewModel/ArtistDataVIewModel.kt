package com.example.spotify.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spotify.repository.SpotifyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtistDataVIewModel @Inject constructor(private val repository: SpotifyRepository): ViewModel() {


    val artistImage: StateFlow<Map<String,List<String>>> = repository.ArtistsImage


     fun fetchArtists(query: String){
        viewModelScope.launch {
            if(query.isEmpty()){
                repository.getArtistsImage("eminem")
            }else {
                repository.getArtistsImage(query)

            }
        }
    }


}