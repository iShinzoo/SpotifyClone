package com.example.spotify.dataclass.GlobalSearch

data class Result(
    val artist: String,
    val description: String,
    val id: String,
    val image: List<Any>,
    val language: String,
    val songIds: String,
    val title: String,
    val type: String,
    val url: String,
    val year: String
)