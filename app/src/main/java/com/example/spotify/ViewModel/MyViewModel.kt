package com.example.spotify.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.get
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(private val savedStateHandle: SavedStateHandle):ViewModel() {
        var password by mutableStateOf(savedStateHandle.get<String>("password") ?: "")
                public set

        var email by mutableStateOf(savedStateHandle.get<String>("email") ?: "")
                public set

        var name by mutableStateOf(savedStateHandle.get<String>("name") ?: "")
                public set

        var gender by mutableStateOf(savedStateHandle.get<String>("gender") ?: "")
                public set

        var dob by mutableStateOf<LocalDate?>(savedStateHandle.get("dob"))
                public set

        var selectedButtonIndices by mutableStateOf(
                savedStateHandle.get<List<Int>>("selectedButtonIndices") ?: emptyList()
        )
                public set

        fun updateSelectedButtonIndex(index: Int) {
                selectedButtonIndices = listOf(index)
        }

        var selectedlangIndx by mutableIntStateOf(-1)

        var selectedArtistIndex by mutableIntStateOf(-1)
}