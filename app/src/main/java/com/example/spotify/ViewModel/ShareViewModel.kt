package com.example.spotify.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.spotify.dataclass.Artists.ArtistResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ShareViewModel:ViewModel() {
    private val _artistResults = MutableStateFlow<List<ArtistResult>>(emptyList())
    val artistResults: StateFlow<List<ArtistResult>> = _artistResults.asStateFlow()


    private val _globalSearchResults = MutableStateFlow<List<ArtistResult>>(emptyList())
    val globalSearchResults: StateFlow<List<ArtistResult>> = _globalSearchResults.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    fun searchArtists(query: String){
        viewModelScope.launch {
            try{
                _isLoading.value = true
                _error.value = null
            }catch (e: Exception) {
                _error.value = e.message
            }finally {
                _isLoading.value = false
            }
        }
    }

    fun clearSearchResults(){
        _artistResults.value = emptyList()
    }
}