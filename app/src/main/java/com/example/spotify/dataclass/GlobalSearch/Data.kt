package com.example.spotify.dataclass.GlobalSearch

data class Data(
    val albums: Albums,
    val artists: Artists,
    val playlists: Playlists,
    val songs: Songs,
    val topQuery: TopQuery
)