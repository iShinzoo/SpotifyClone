package com.example.spotify.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.time.LocalDate

class MyViewModel:ViewModel() {
    var password by mutableStateOf("")
    var email by  mutableStateOf("")
    var name by mutableStateOf("")
    var gender by mutableStateOf("")
    var dob by mutableStateOf<LocalDate?>(null)
    var selectedButtonIndices by mutableStateOf(mutableStateListOf<Int>())

}