package com.example.spotify.dataclass

import java.time.LocalDate

data class User(val email: String="", val password: String="", val name: String="", val dob : LocalDate?=null, val gen: String="")

data class ButtonState(
    var isSelected: Boolean=false,
    var text: String="",
    var color: androidx.compose.ui.graphics.Color
)

data class Genre(
    val name: String,
    val image: Int,
    val background: Any
)

data class ArtistSelected(
    val name: String,
)