package com.example.spotify.repository

import com.example.spotify.api.SpotifyAPI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class SpotifyRepository @Inject constructor(private val spotifyAPI: SpotifyAPI) {
    private val _ArtistsImage = MutableStateFlow<Map<String,List<String>>>(emptyMap())
    val ArtistsImage: StateFlow<Map<String,List<String>>> = _ArtistsImage.asStateFlow()


    suspend fun getArtistsImage(query: String){
        val response = spotifyAPI.getArtists(query)
        if(response.isSuccessful && response.body() != null){
            val artists = response.body()
            val imageUrls = artists?.data?.results?.mapNotNull { result ->
                result.image.maxByOrNull { it.quality.split("x").first()
                    .toIntOrNull()?:0}?.url
                } ?: emptyList()

                _ArtistsImage.value = _ArtistsImage.value + (query to imageUrls)
            // Do something with the artists
        }else{

        }
    }
}