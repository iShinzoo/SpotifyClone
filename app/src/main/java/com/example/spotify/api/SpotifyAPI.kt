package com.example.spotify.api

import com.example.spotify.dataclass.Artists.ArtistResult
import com.example.spotify.dataclass.Artists.Image
import com.example.spotify.dataclass.Artists.SearchForArtistsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SpotifyAPI {

    @GET("/api/search/artists")
    suspend fun getArtists(@Query("query") SearchQuery: String): Response<SearchForArtistsResponse>


}