package com.example.spotify.dataclass.Artists

data class ArtistResult(
    val id: String,
    val image: List<Image>,
    val name: String,
    val role: String,
    val type: String,
    val url: String
)
