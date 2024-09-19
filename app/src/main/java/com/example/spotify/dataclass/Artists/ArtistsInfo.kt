package com.example.spotify.dataclass.Artists

data class SearchForArtistsResponse(
    val data: SearchForArtistsData,
    val success: Boolean
)