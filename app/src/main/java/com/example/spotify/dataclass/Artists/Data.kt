package com.example.spotify.dataclass.Artists

data class SearchForArtistsData(
    val results: List<ArtistResult>,
    val start: Int,
    val total: Int
)